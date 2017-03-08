import controllers.{Assets, BaseController}
import play.api.ApplicationLoader.Context
import play.api._
import router.Routes

class AppLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new ApplicationComponents(context).application
  }
}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context) {
  lazy val applicationController = new BaseController()
  lazy val assets = new Assets(httpErrorHandler)

  lazy val router = new Routes(httpErrorHandler, applicationController, assets)

}
