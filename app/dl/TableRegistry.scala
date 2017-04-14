package dl

import slick.lifted.TableQuery

trait TableRegistry {
  def users: TableQuery[Users]
  def profiles: TableQuery[Profiles]
  def matches: TableQuery[Matches]
}
