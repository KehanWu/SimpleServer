package servlet

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

import scala.util.Try

object Launcher {
  def main(args: Array[String]): Unit = {
    val port = sys.env.get("PORT") match {
      case Some(portNumber) =>
        Try(portNumber.toInt).getOrElse(8080)
      case None =>
        8080
    }

    val server = new Server(port)
    val context = new WebAppContext()
//    context.setInitParameter(ScalatraListener.LifeCycleKey, "src/main/scala")
    context.setContextPath("/")
    context.setResourceBase("src/main/webapp")
    context.setEventListeners(Array (new ScalatraListener))

    server.setHandler(context)

    server.start()
    server.join()
  }
}
