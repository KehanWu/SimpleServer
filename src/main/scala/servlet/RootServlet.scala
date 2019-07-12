package servlet

import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport._
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import org.json4s.jackson.JsonMethods
import org.json4s.JsonDSL._


class RootServlet {

  def home: Route = get { (path("") | pathSingleSlash) {
    complete(
      <html>
        <body>
          <h1>Kehan Wu's Scala Application.</h1>
          See <a href ="https://github.com/KehanWu/SimpleServer"> Repo </a> for more information.
        </body>
      </html>
    )
  }}

  def health: Route = get { (path("__health") | path("health")) {
    complete(JsonMethods.pretty("message" -> "Congratulations! Kehan's APP is alive.") + "\n")
  }}


  def routes: Route = {
    home ~ health
  }

}
