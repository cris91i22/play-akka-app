package dl.modelinteract

import dl._
import domain.{Profile, User}

trait ModelInteractProfile extends ModelInteract[Profile] {
  type D = ProfileDAO
  type Q = Profiles
}

object ModelInteractProfile extends ModelInteractProfile {
  implicit def upConvert(dao: ProfileDAO): Profile = Profile(
    dao.id,
    dao.userId,
    dao.genera,
    dao.gameType,
    dao.difficulty,
    dao.free,
    dao.cost
  )

  implicit def downConvert(model: Profile): ProfileDAO = ProfileDAO(
    model.id,
    model.userId,
    model.genera,
    model.gameType,
    model.difficulty,
    model.free,
    model.cost
  )

  def table(tRegistry: TableRegistry) = tRegistry.profiles
}
