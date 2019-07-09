package servlet

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.Logger
import models.{Comment, JsonSupport}
import akka.http.scaladsl.server.Directives._
import spray.json.JsValue

class CommentHandler (urlPathHead: String) extends JsonSupport {

  private val logger = Logger(getClass)

  private def submitComment(): Route = post {
    (pathPrefix(urlPathHead) & extractRequestContext & entity(as[Comment])) {
      case (requestContext, comment) =>
        complete(s"Comment is $comment")
    }
  }


  def routes: Route = submitComment()

}
