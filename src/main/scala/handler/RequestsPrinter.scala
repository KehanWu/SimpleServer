package handler

import java.util.UUID

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import datastorage.mysql.RequestEntry

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatra.{Accepted, ActionResult, Ok}

import scala.concurrent.Future

class RequestsPrinter(implicit as: ActorSystem, mat: ActorMaterializer) {

  def printRequest(namespace: String, uuid: String)= {
    Future {
      Thread.sleep(500)
      Accepted(body = s"Namespace is: $namespace, uuid is $uuid.")
    }
  }

}



object RequetsPrinter {

  def apply(implicit as: ActorSystem, mat: ActorMaterializer): RequestsPrinter = new RequestsPrinter()

}