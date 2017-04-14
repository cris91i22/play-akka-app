package dl.modelinteract

import java.sql.Date

import dl.{MatchDAO, Matches, TableRegistry}
import domain.Match

trait ModelInteractMatch extends ModelInteract[Match] {
  type D = MatchDAO
  type Q = Matches
}

object ModelInteractMatch extends ModelInteractMatch {
  implicit def upConvert(dao: MatchDAO): Match = Match(
    dao.id,
    dao.userId1,
    dao.profileIdUser1,
    dao.userId2,
    dao.profileIdUser2,
    dao.date.toLocalDate,
    dao.available
  )

  implicit def downConvert(model: Match): MatchDAO = MatchDAO(
    model.id,
    model.userId1,
    model.profileIdUser1,
    model.userId2,
    model.profileIdUser2,
    Date.valueOf(model.date),
    model.available
  )

  def table(tRegistry: TableRegistry) = tRegistry.matches
}