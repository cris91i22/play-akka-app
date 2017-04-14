package dl

import java.sql.Date
import java.time.LocalDate

import utils.ExtendedPostgresDriver.api._

trait WithStandardID[T] extends Table[T] {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
}

class Users(tag: Tag) extends Table[UserDAO](tag, "users") with WithStandardID[UserDAO] {
  def name = column[String]("name")
  def email = column[String]("email")

  def * = (id.?, name, email) <> (UserDAO.tupled, UserDAO.unapply)
}

class Profiles(tag: Tag) extends Table[ProfileDAO](tag, "profiles") with WithStandardID[ProfileDAO] {
  def userId = column[Int]("userId")
  def genera = column[String]("genera")
  def gameType = column[String]("gameType")
  def difficulty = column[Int]("difficulty")
  def free = column[Boolean]("free")
  def cost = column[String]("cost")

  def userFk = foreignKey("FLOW_USR_FK", userId, TableQueries.users)(_.id, onDelete=ForeignKeyAction.Cascade)

  def * = (id.?, userId, genera, gameType, difficulty, free, cost) <> (ProfileDAO.tupled, ProfileDAO.unapply)
}

class Matches(tag: Tag) extends Table[MatchDAO](tag, "matches") with WithStandardID[MatchDAO] {
  def userId1 = column[Int]("userId1")
  def profileIdUser1 = column[Int]("profileIdUser1")
  def userId2 = column[Int]("userId2")
  def profileIdUser2 = column[Int]("profileIdUser2")
  def date = column[Date]("date")
  def available = column[Boolean]("available")

  def user1Fk = foreignKey("FLOW_USR1_FK", userId1, TableQueries.users)(_.id, onDelete=ForeignKeyAction.Cascade)
  def user2Fk = foreignKey("FLOW_USR2_FK", userId2, TableQueries.users)(_.id, onDelete=ForeignKeyAction.Cascade)
  def profile1Fk = foreignKey("FLOW_PROF1_FK", profileIdUser1, TableQueries.profiles)(_.id, onDelete=ForeignKeyAction.Cascade)
  def profile2Fk = foreignKey("FLOW_PROF2_FK", profileIdUser2, TableQueries.profiles)(_.id, onDelete=ForeignKeyAction.Cascade)

  def * = (id.?, userId1, profileIdUser1, userId2, profileIdUser2, date, available) <> (MatchDAO.tupled, MatchDAO.unapply)
}

object TableQueries extends TableRegistry {

  val users = TableQuery[Users]
  val profiles = TableQuery[Profiles]
  val matches = TableQuery[Matches]

  // This list should always be ordered such that a DDL created from the sequence is ordered correctly (i.e. If table A has a
  // foreign key to table B then table B should appear first in the sequence).
  val all = Seq(
    users,
    profiles,
    matches
  )

  def createActions() = all.map(_.schema.create)

  def dropActions() = all.reverse.map(_.schema.drop)
}
