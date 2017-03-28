package dl.storage

import dl.utils.ExtendedPostgresDriver.api._
import dl.TableRegistry

import scala.concurrent.ExecutionContext

class ExampleStorage(val db: Database, val tRegistry: TableRegistry)
                    (implicit val ec: ExecutionContext) extends Storage {

//  import ModelInteract._
//
//  val users: BasicObjectStorage[User] = new ObjectStorage[User](db, tRegistry)
//  val messages: BasicObjectStorage[Message] = new ObjectStorage[Message](db, tRegistry)
//
//  val countUsers: CountUsersQuery = new CountUsersQuery(db, tRegistry)
//  val retrieveUsersWithMessages: UsersWithMessagesQuery = new UsersWithMessagesQuery(db, tRegistry)

}