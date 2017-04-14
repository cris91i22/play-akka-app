package dl

import java.sql.Date

trait StandardDAOBase[T] {
  def id: Option[Int]
  def withID(id: Int): T
}

case class ProfileDAO(id: Option[Int] = None,
                      userId: Int,
                      genera: String,
                      gameType: String,
                      difficulty: Int,
                      free: Boolean,
                      cost: String) extends StandardDAOBase[ProfileDAO] {
  def withID(id: Int) = this.copy(id = Some(id))
}

case class UserDAO(id: Option[Int] = None,
                   name: String,
                   email: String) extends StandardDAOBase[UserDAO] {
  def withID(id: Int) = this.copy(id = Some(id))
}

case class MatchDAO(id: Option[Int] = None,
                    userId1: Int,
                    profileIdUser1: Int,
                    userId2: Int,
                    profileIdUser2: Int,
                    date: Date,
                    available: Boolean) extends StandardDAOBase[MatchDAO] {
  def withID(id: Int) = this.copy(id = Some(id))
}