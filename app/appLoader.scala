import akka.actor.ActorRef
import controllers.{Assets, BaseController}
import dl.storage.ExampleStorage
import dl.{ExampleDB, TableQueries}
import play.api.ApplicationLoader.Context
import play.api._
import router.Routes
import services.CoreObjectServiceActor


class AppLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new ApplicationComponents(context).application
  }
}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context) {
  implicit val ec = actorSystem.dispatcher

  // Db Config
  val igniteDB = new ExampleDB()
  val storage = new ExampleStorage(igniteDB.instance, TableQueries)

  TableQueries.createActions()

  // Actors
  val coreObjectServiceActor: ActorRef = actorSystem.actorOf(CoreObjectServiceActor.props(storage), CoreObjectServiceActor.Name)

  // Controllers
  lazy val applicationController = new BaseController(coreObjectServiceActor)
  lazy val assets = new Assets(httpErrorHandler)

  lazy val router = new Routes(httpErrorHandler, applicationController, assets)

}
