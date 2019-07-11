package servlet

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.Logger
import models.{Comment, JsonSupport}
import akka.http.scaladsl.server.Directives._
import db.DataStorageTrait
import spray.json.JsValue

class CommentHandler(urlPathHead: String, db: DataStorageTrait) extends JsonSupport {

  private val logger = Logger(getClass)

  private def submitComment(): Route = post {
    (pathPrefix(urlPathHead) & extractRequestContext & entity(as[Comment])) {
      case (requestContext, comment) =>
        db.insertCommentViaID(comment)
        logger.info(s"====== Inserting comment : $comment")
        complete(s"Comment is $comment")
    }
  }


  def routes: Route = submitComment()

}
