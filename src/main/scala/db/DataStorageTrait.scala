package db

import java.util.UUID

import models.Comment

trait DataStorageTrait {
  def insertCommentViaID(comment: Comment): Unit
}
