package servlet
import org.scalatra._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext



class UiServlet extends ScalatraServlet {
  def executor: ExecutionContext = global

  val healthMessage =
    """{
      |"Message" : "Kehan's Server is alive."
      |
      |}""".stripMargin


  get("/") {
    halt(200, healthMessage)
  }

  get("/__health") {
    halt(200, healthMessage)
  }


}
