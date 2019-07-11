package db

import java.util.UUID

import models.Comment
import org.joda.time.DateTime

import scala.slick.lifted.Tag
import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._


class CommentsTable(tag: Tag) extends Table[Comment](tag, "comments") {

  def id = column[UUID]("id")
  def author = column[String]("author")
  def content = column[String]("content")
  def lastModifyDate = column[DateTime]("lastmodifydate")
  def * = (id, author, content, lastModifyDate) <>(Comment.tupled, Comment.unapply)

}
