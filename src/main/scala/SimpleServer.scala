import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import servlet.{ApiV1Servlet, RootServlet}

import scala.util.Try

object SimpleServer {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val bindingFuture = Http().bindAndHandle(
      (new RootServlet).routes ~
      new ApiV1Servlet().routes,
      "0.0.0.0",
      Try { sys.env("PORT").toInt.abs }.getOrElse(8080))

    println("Server running ...")
  }

}
