import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisherMessage.Request
import javax.servlet.ServletContext
import org.joda.time.DateTime
import org.scalatra.LifeCycle
import servlet.{ApiServlet, UiServlet}
import handler.RequestsPrinter

import scala.concurrent.ExecutionContext

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext): Unit = {
    implicit val act = ActorSystem(s"Simple_Server")
    implicit val mat = ActorMaterializer()
    implicit val executionContext = scala.concurrent.ExecutionContext.global

    val requestsPrinter = new RequestsPrinter()
    context.mount(new UiServlet, "/")
    context.mount(new ApiServlet(requestsPrinter), "/api/v1")
  }
}

