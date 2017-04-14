package utils

import play.api.libs.json._
import play.api.mvc.{Request, Result}
import scala.concurrent.{ExecutionContext, Future}

object ResponsesHelper {

  def controllerResponseHandler[T](response: Future[T])(implicit ex: ExecutionContext, writer: Writes[T]): Future[Result] = {
    import play.api.mvc.Results._
    response.map { res =>
      Ok(Json.toJson(res))
    }.recover {
      case e: Exception =>
        InternalServerError(s"Error: $e")
    }
  }

  /**
    * This method allows me to transform an Option to a Future.
    *
    * @param opt
    * @tparam T
    * @return Future of T type
    */
  def toFuture[T](opt: Option[T]): Future[T] = opt match {
    case Some(value) => Future.successful(value)
    case None => Future.failed(new NoSuchElementException)
  }

  /**
    * Receive a request and validate the model
    *
    * @param request from Play Fw
    * @param f expecting the Format to make the model validations
    * @tparam T the type of the request model that you want to validate
    * @return Future, successful in case of valid model, failure the opposite
    */
  def validateRequest[T](request: Request[JsValue])(implicit f: Format[T]): Future[T] = request.body.validate[T] match {
    case JsSuccess(value, _) => Future.successful(value)
    case JsError(errors) => Future.failed(new RuntimeException(errors.flatMap(_._2).mkString(", ")))
  }

}
