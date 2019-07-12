package db

import java.util.UUID

import akka.Done
import db.postgre.Comment
import servlet.PostgreError


trait DataStorageTrait {
  def insertComment(comment: Comment): Either[PostgreError, Done]

  def getComment(id: UUID): Option[Comment]
}
