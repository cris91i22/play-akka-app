package dl

import com.typesafe.config.{Config, ConfigFactory}
import dl.utils.ExtendedPostgresDriver.api._
import play.api.Logger

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class ExampleDB(config: Config = ConfigFactory.load)(implicit ec: ExecutionContext) {

  final val configName = "db"
  final val instance = {
    val i = createInstance(config)
    migrate(i)
    i
  }

  def shutdown() {
    try {
      Logger.debug("Shutting down db instance.")
      Await.result(instance.shutdown, 2 seconds)
    }
    catch {
      case e: Throwable => Logger.error(s"DB instance shutdown failed", e)
    }
  }

  private def migrate(i: Database) = {
//    try {
//      logger.info("Running migration...")
//      MigrationAssistant.migrate(i)
//    }
//    catch {
//      case e: Throwable => {
//        logger.error("Migration failed!")
//        throw(e)
//      }
//    }
  }

  private def createInstance(config: Config) = {
    try {
      Logger.debug(s"Using Configuration: $config")
      Database.forConfig(configName, config)
    }
    catch {
      case e: Throwable => {
        Logger.error("DB Instance could not be created.", e)
        throw e
      }
    }
  }
}