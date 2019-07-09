import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{MalformedQueryParamRejection, MalformedRequestContentRejection, RejectionHandler}
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.Logger
import servlet.{ApiV1Servlet, RootServlet}

import scala.concurrent.ExecutionContextExecutor
import scala.util.Try

object SimpleServer {
  private val logger = Logger(getClass)

  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("my-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher


    implicit def rejectionHandler =
      RejectionHandler.newBuilder()
        .handleNotFound(complete(StatusCodes.NotFound, "Oh man, what you are looking for is long gone."))
        .handle {
          case MalformedRequestContentRejection(msg, e) => complete(HttpResponse(StatusCodes.BadRequest, entity = HttpEntity(e.getMessage)))
          case MalformedQueryParamRejection(parameterName, errorMsg, maybeThrowable) =>
            maybeThrowable match {
              case Some(e) => complete(HttpResponse(StatusCodes.BadRequest, entity = HttpEntity(e.getMessage)))
              case None    => complete(HttpResponse(StatusCodes.BadRequest))
            }
        }
      .result()

    val bindingFuture = Http().bindAndHandle(
      (new RootServlet).routes ~
      new ApiV1Servlet().routes,
      "0.0.0.0",
      Try { sys.env("PORT").toInt.abs }.getOrElse(8080))

    logger.info("Server running ...")
  }

}
