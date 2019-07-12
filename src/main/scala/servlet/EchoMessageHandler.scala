package servlet

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCode, StatusCodes}
import models.JsonSupport
import akka.http.scaladsl.server.{RejectionHandler, Route}
import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.Logger
import spray.json.JsValue


class EchoMessageHandler(urlPathHead: String) extends JsonSupport {

  private val logger = Logger(getClass)


  private def echoMessage(): Route = post {
    (path(urlPathHead) & extractClientIP & entity(as[JsValue]) ) {
      case (ip, json) =>
        logger.info(s"IP is $ip, JsonBody is: $json")
        complete(HttpResponse(status = StatusCodes.OK, entity = HttpEntity(json.toString)))
    }
  }


  def routes: Route = echoMessage()
}
