package servlet

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import models.{Comment, JsonSupport, TestMessage}

class ApiV1Servlet() extends JsonSupport{

  def routes: Route = {
    path("api") {
      post {
        entity(as[TestMessage]) { message =>
          println(s"Post $message received.")
          complete(s"Receoved $message")
        }
      }
    }
  }
}
