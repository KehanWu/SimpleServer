package servlet

import java.util.UUID

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, entity, post}
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.Logger
import models.{AuthorContentPair, JsonSupport}
import akka.http.scaladsl.server.Directives._
import db.DataStorageTrait
import db.postgre.Comment
import org.joda.time.DateTime


class CommentHandler(urlPathHead: String, db: DataStorageTrait) extends JsonSupport with ServerStack {

  private val logger = Logger(getClass)

  private def submitComment(): Route = post {
    (pathPrefix(urlPathHead) & entity(as[AuthorContentPair])) {
      case AuthorContentPair(author, content) =>
        val created = DateTime.now
        val comment = Comment(UUID.randomUUID, author, content, created)
        logger.info(s"Generated: $comment at $created.")
        db.insertComment(comment) match {
          case Left(error: PostgreError) => complete(internalError(error.exc))
          case Right(_) => complete(HttpResponse(status = StatusCodes.OK, entity = HttpEntity(s"Insert: $comment successfully.")))
        }
    }
  } ~ get {
    parameter('id.?) { id => {
      id match {
        case Some(commentId) => complete(s"Searching for comment with ID: $commentId.")
        case _ => complete("No id provided, search for all comments.")
      }
    }
    }
  }

  def routes: Route = submitComment()

}
