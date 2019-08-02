package servlet

import akka.Done
import akka.http.scaladsl.model.{HttpEntity, MediaTypes, StatusCodes}
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import db.DataStorageTrait
import models.{AuthorContentPair, JsonSupport}
import org.json4s.jackson.JsonMethods
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class ServletUnitSpec extends FlatSpec with MockFactory with Matchers with ScalatestRouteTest with JsonSupport {

  val mockPostgre = mock[DataStorageTrait]

  val apiV1servlet = new ApiV1Servlet(mockPostgre)
  val routes = apiV1servlet.routes

  s"GET /api/v1/" should "return health message" in {
    Get("/api/v1") ~> Route.seal(routes) ~> check {
      status should be(StatusCodes.OK)
    }
  }

  s"POST /api/v1/echo" should "return same echo message" in {
    val jsString: String =
      """
        |{
        | "id": 1,
        | "name": "me",
        | "description": "awesome"
        |}
      """.stripMargin
    val entity = HttpEntity(MediaTypes.`application/json`, jsString)

    Post("/api/v1/echo", entity) ~> Route.seal(routes) ~> check {
      status should be(StatusCodes.OK)
      (JsonMethods.parse(responseAs[String]) \ "id").values should be(1)
      (JsonMethods.parse(responseAs[String]) \ "name").values should be("me")
      (JsonMethods.parse(responseAs[String]) \ "description").values should be("awesome")
    }
  }

  s"GET /api/v1/comment" should "return correct messages" in {
    Get("/api/v1/comment") ~> Route.seal(routes) ~> check {
      status should be(StatusCodes.OK)
      responseAs[String] should be("No id provided, search for all comments.")
    }
  }

  s"GET /api/v1/comment?id=123" should "return correct messages" in {
    Get("/api/v1/comment?id=123") ~> Route.seal(routes) ~> check {
      status should be(StatusCodes.OK)
      responseAs[String] should be("Searching for comment with ID: 123.")
    }
  }

  s"POST /api/v1/comment" should "return correct message" in {
    (mockPostgre.insertComment _).expects(*).returning(Right(Done)).once
    val author = "kehan_wu"
    val content = "This is a test message."
    val authorContentPair = AuthorContentPair(author, content)
    Post("/api/v1/comment", authorContentPair) ~> Route.seal(routes) ~> check {
      status should be(StatusCodes.OK)
      responseAs[String].contains(content) should be(true)
    }
  }

}
