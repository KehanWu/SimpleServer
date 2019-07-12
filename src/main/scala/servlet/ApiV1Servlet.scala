package servlet

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.Logger
import models.JsonSupport
import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import db.DataStorageTrait

class ApiV1Servlet(db: DataStorageTrait) extends JsonSupport {
  val logger = Logger(this.getClass)

  private val messageHandler = new EchoMessageHandler("echo")

  private val commentHandler = new CommentHandler("comment", db)


  private[servlet] def home: Route = get {
    pathEnd {
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
    }
  }


  def routes: Route = {
    logRequestResult("SimpleServer-API-v1-request") {
      pathPrefix("api" / "v1") {
        home ~
          messageHandler.routes ~
          commentHandler.routes
      }
    }
  }
}
