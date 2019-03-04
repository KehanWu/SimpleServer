package servlet

import handler.RequestsPrinter
import org.scalatra._

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.duration._

class ApiServlet(requestsPrinter: RequestsPrinter)
                (implicit executionContext: ExecutionContext)
  extends ScalatraServlet with FutureSupport{

  override protected implicit def executor: ExecutionContext = executionContext

  get("/:namespace/:uuid") {
    val nameSpace = params("namespace")
    val uuid = params("uuid")
    new AsyncResult {
      override def timeout: Duration = 1.second
      val is = requestsPrinter.printRequest(nameSpace, uuid)
    }

  }


}
