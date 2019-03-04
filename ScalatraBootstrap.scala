import javax.servlet.ServletContext
import org.scalatra.LifeCycle
import servlet.UiServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    context.mount(new UiServlet, "/")
  }
}