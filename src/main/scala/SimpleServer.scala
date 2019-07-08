import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.util.Try

object SimpleServer {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say Love U to 姚雨惟</h1>"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", Try { sys.env("PORT").toInt.abs }.getOrElse(8080))

    println(s"Server online at http://localhost:8080/")

  }

}
