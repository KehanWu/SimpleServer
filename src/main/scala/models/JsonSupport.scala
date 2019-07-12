package models

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.joda.time.DateTime
import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat, _}


case class AuthorContentPair(author: String, content: String)

trait JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {

  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(uuid: UUID) = JsString(uuid.toString)
    def read(value: JsValue) = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _ => deserializationError("UUID expected.")
    }
  }

  implicit object DateTimeFormat extends JsonFormat[DateTime] {
    def write(dateTime: DateTime) = JsString(dateTime.toString)
    def read(value: JsValue) = value match {
      case JsString(datetime) => DateTime.parse(datetime)
      case _ => deserializationError("DateTime expected.")
    }
  }

  implicit val authorContentFormat = jsonFormat2(AuthorContentPair)
}