package db

import akka.Done
import models.Comment

trait DataStorageTrait {
  def insertCommentViaID(comment: Comment): Done
}
