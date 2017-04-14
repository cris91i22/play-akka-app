package domain

import play.api.libs.json.{Format, Json}

case class UserRequest(name: String,
                      email: String)

object UserRequest {
  implicit val userRequestFormat: Format[UserRequest] = Json.format[UserRequest]
}
