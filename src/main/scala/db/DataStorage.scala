package db

import akka.Done
import com.typesafe.scalalogging.Logger
import models.Comment

import scala.slick.driver.PostgresDriver.backend.DatabaseDef
import scala.slick.driver.PostgresDriver.simple._
import scala.util.Try

class DataStorage(postgreDef: DatabaseDef) extends DataStorageTrait {
  private val logger = Logger(getClass)

  override def insertCommentViaID(comment: Comment): Done = postgreDef withSession {
    implicit session =>
      Try {
        val comments = TableQuery[CommentsTable]
        comments.insert(comment)
      }.recover {
        case e: Exception =>
          logger.info(s"An exception happened when retrieve comment ID: ${comment.id} from Postgres. ${e.getMessage}")
      }
      Done
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
