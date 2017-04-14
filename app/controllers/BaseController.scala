package controllers

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import domain.{User, UserRequest}
import play.api.libs.json._
import play.api.mvc._
import services.CoreObjectServiceActor.CreateUserMessage
import utils.ResponsesHelper._
import utils.integration.defaultExecContext
import scala.concurrent.duration._

class BaseController(val coreObjectServiceActor: ActorRef) extends Controller {

  implicit val timeout: Timeout = Timeout(5 seconds)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def createUser: Action[JsValue] = Action.async(parse.json){ implicit request =>
    val r = for{
      userRequest <- validateRequest[UserRequest](request)
      response <- (coreObjectServiceActor ? CreateUserMessage(userRequest)).mapTo[User]
    } yield response
    controllerResponseHandler(r)
  }

}