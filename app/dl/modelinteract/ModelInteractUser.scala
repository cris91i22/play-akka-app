package dl.modelinteract

import dl.{TableRegistry, UserDAO, Users}
import domain.User

trait ModelInteractUser extends ModelInteract[User] {
  type D = UserDAO
  type Q = Users
}

object ModelInteractUser extends ModelInteractUser {
  implicit def upConvert(dao: UserDAO): User = User(
    dao.id,
    dao.name,
    dao.email
  )

  implicit def downConvert(model: User): UserDAO = UserDAO(
    model.id,
    model.name,
    model.email
  )

  def table(tRegistry: TableRegistry) = tRegistry.users
}
