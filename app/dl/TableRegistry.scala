package dl

import slick.lifted.TableQuery

trait TableRegistry {
  def users: TableQuery[Users]
  def messages: TableQuery[Messages]
}
