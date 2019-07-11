package db

import java.util.UUID

import com.typesafe.scalalogging.Logger
import models.Comment

import scala.slick.driver.PostgresDriver.backend.DatabaseDef
import scala.slick.driver.PostgresDriver.simple._
import scala.util.Try

class DataStorage(postgreDef: DatabaseDef) extends DataStorageTrait {
  private val logger = Logger(getClass)

  override def insertCommentViaID(comment: Comment): Unit = postgreDef withSession {
    implicit session =>
      Try {
        val comments = TableQuery[CommentsTable]
        comments.insert(comment)
      }.recover {
        case e: Exception =>
          logger.info(s"An exception happened when retrieve comment ID: ${comment.id} from Postgres. ${e.getMessage}")
      }
  }
}


object DataStorage {
  def apply(postgreDef: DatabaseDef): DataStorage = new DataStorage(postgreDef)
}
