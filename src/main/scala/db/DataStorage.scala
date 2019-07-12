package db

import akka.Done
import com.typesafe.scalalogging.Logger
import db.postgre.{Comment, CommentsTable}
import servlet.{ApiV1ServletError, PostgreError}

import scala.slick.driver.PostgresDriver.backend.DatabaseDef
import scala.slick.driver.PostgresDriver.simple._
import scala.util.Try

class DataStorage(postgreDef: DatabaseDef) extends DataStorageTrait {
  private val logger = Logger(getClass)

  override def insertComment(comment: Comment): Either[PostgreError, Done] = postgreDef withSession {
    implicit session =>
      Try {
        val comments = TableQuery[CommentsTable]
        comments.insert(comment)
        Right(Done)
      }.recover {
        case e: Exception =>
          logger.warn(s"Faild to insert comment $comment to Postgres due to ${e.getMessage}")
          Left(PostgreError(e, s"Failed to insert comment $comment"))
      }.get
  }
}


object DataStorage {
  def apply(postgreDef: DatabaseDef): DataStorage = {
    initDB(postgreDef)
    new DataStorage(postgreDef)
  }

  def initDB(postgreDef: DatabaseDef): Done = {
    val comments = TableQuery[CommentsTable]
    postgreDef withSession { implicit session =>
      import scala.slick.jdbc.meta.MTable
      if (MTable.getTables(comments.baseTableRow.tableName).list.isEmpty) {
        comments.ddl.create
      }
      Done
    }
  }
}
