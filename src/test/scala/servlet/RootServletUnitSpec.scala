package servlet

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers, WordSpec}

class RootServletUnitSpec extends FlatSpec with MockFactory with Matchers with ScalatestRouteTest {

  val servlet = new RootServlet()

  "GET homepage" should "return 200" in {
    Get() ~> Route.seal(servlet.home) ~> check {
      status should be(StatusCodes.OK)
    }
  }

  "GET health page" should "return 200" in {
    Get("/health") ~> Route.seal(servlet.health) ~> check {
      status should be(StatusCodes.OK)
    }
  }

}
