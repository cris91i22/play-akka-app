package dl.storage

import dl.utils.ExtendedPostgresDriver.api._
import dl.TableRegistry
import domain.{Match, Profile, User}

import scala.concurrent.ExecutionContext

class ExampleStorage(val db: Database, val tRegistry: TableRegistry)
                    (implicit val ec: ExecutionContext) extends Storage {

  import dl.modelinteract.ModelInteract._

  val users: BasicObjectStorage[User] = new ObjectStorage[User](db, tRegistry)
  val profiles: BasicObjectStorage[Profile] = new ObjectStorage[Profile](db, tRegistry)
  val matches: BasicObjectStorage[Match] = new ObjectStorage[Match](db, tRegistry)

//  val countUsers: CountUsersQuery = new CountUsersQuery(db, tRegistry)
//  val retrieveUsersWithMessages: UsersWithMessagesQuery = new UsersWithMessagesQuery(db, tRegistry)

}