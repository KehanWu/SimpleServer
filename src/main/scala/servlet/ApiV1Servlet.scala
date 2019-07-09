package servlet

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.typesafe.scalalogging.Logger
import models.{Comment, JsonSupport, TestMessage}
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import org.json4s.jackson

class ApiV1Servlet() extends JsonSupport{
  val logger = Logger(this.getClass)


  private val messageHandler = new EchoMessageHandler("echo")


  private[servlet] def home: Route = get { pathEnd {
    complete(
      <html>
        <body>
          <h1>Simple Server API v1</h1>
          See
          <a href="https://github.com/KehanWu/SimpleServer">Github Repo</a>
          for more information.
        </body>
      </html>
    )
  }}


  def routes: Route = {
    pathPrefix("api" / "v1") {
      home ~
      messageHandler.routes
    }
  }
}
