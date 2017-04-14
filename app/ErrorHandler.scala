import play.api.http.HttpErrorHandler
import play.api.mvc.Results.{Forbidden, InternalServerError}
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

object ErrorHandler extends HttpErrorHandler {

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      Forbidden("A client error occurred: " + message)
    )
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}
