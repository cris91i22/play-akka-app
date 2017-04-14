package services

import akka.actor.{Actor, ActorRef, Props}
import dl.storage.Storage
import domain._
import play.api.Logger
import services.CoreObjectServiceActor._

class CoreObjectServiceActor(storage: Storage) extends Actor {

  override def receive: Receive = {
    case message: CoreObjectMessage => receiveLocal(sender(), message)
  }

  def receiveLocal(ref: ActorRef, message: CoreObjectMessage): Unit = {
    Logger.info("Receive core actor message to perform basic operations...")
    message match {
      case CreateUserMessage(req) => sender() ! User(Some(1), "","")  //storage.users.create(User(name = req.name, email = req.email))
      case CreateProfileMessage(prof) => sender() ! storage.profiles.create(prof)
      case CreateMatchMessage(matc) => sender() ! storage.matches.create(matc)
    }
  }

}

sealed trait CoreObjectMessage

object CoreObjectServiceActor {

  val Name = "core-service"

  def props(storage: Storage) = Props(new CoreObjectServiceActor(storage))

  case class CreateUserMessage(usr: UserRequest) extends CoreObjectMessage
  case class CreateProfileMessage(profile: Profile) extends CoreObjectMessage
  case class CreateMatchMessage(matc: Match) extends CoreObjectMessage


}
