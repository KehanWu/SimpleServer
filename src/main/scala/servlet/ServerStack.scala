package servlet

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import com.typesafe.scalalogging.Logger
import org.json4s.jackson.JsonMethods
import org.json4s.JsonDSL._

trait ServerStack {
  private val logger = Logger(getClass)

  def internalError(exc: Throwable): HttpResponse = {
    val detail = s"An unexpected internal error occurred."
    val jsonBody = ("message" -> detail) ~
      ("exceptions" -> List(exc.getMessage))
    HttpResponse(
      StatusCodes.InternalServerError,
      entity = HttpEntity(
        ContentTypes.`application/json`,
        JsonMethods.pretty(jsonBody) + "\n"
      )
    )
  }

  def badRequest(exc: Throwable, msg: String): HttpResponse = {
    val jsonBody = ("message" -> msg) ~
      ("exceptions" -> List(exc.getMessage))
    HttpResponse(
      StatusCodes.BadRequest,
      entity = HttpEntity(
        ContentTypes.`application/json`,
        JsonMethods.pretty(jsonBody) + "\n"
      )
    )
  }

  def notFound(exc: Throwable, msg: String): HttpResponse = {
    val jsonBody = ("message" -> msg) ~
      ("exceptions" -> List(exc.getMessage))
    HttpResponse(
      StatusCodes.NotFound,
      entity = HttpEntity(
        ContentTypes.`application/json`,
        JsonMethods.pretty(jsonBody) + "\n"
      )
    )
  }

}
