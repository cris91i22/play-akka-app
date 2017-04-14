package domain

import java.time.LocalDate

import play.api.libs.json.{Format, Json}

case class User(id: Option[Int] = None,
                name: String,
                email: String,
                profiles: Seq[Profile] = Seq.empty)

object User {
  implicit val userFormat: Format[User] = Json.format[User]
}

case class Profile(id: Option[Int] = None,
                   userId: Int,
                   genera: String, // Action, adventure, gun game, fantasy, basic game
                   gameType: String, // rol, rpg, firs person, cooperative
                   difficulty: Int, // 10 20 30 ....
                   free: Boolean,
                   cost: String)// Cheap, medium, expensive

object Profile {
  implicit val profileFormat: Format[Profile] = Json.format[Profile]
}

case class Match(id: Option[Int] = None,
                 userId1: Int,
                 profileIdUser1: Int,
                 userId2: Int,
                 profileIdUser2: Int,
                 date: LocalDate,
                 available: Boolean)

object Match {
  implicit val matchFormat: Format[Match] = Json.format[Match]
}

