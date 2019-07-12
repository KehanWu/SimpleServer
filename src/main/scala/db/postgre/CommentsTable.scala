package db.postgre

import java.util.UUID

import org.joda.time.DateTime

import scala.slick.lifted.Tag
import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._

case class Comment(id: UUID, author: String, content: String, lastModifyDate: DateTime)

class CommentsTable(tag: Tag) extends Table[Comment](tag, "comments") {

  def id = column[UUID]("id", O.PrimaryKey)
  def author = column[String]("author")
  def content = column[String]("content")
  def created = column[DateTime]("created")
  def * = (id, author, content, created) <>(Comment.tupled, Comment.unapply)

}
