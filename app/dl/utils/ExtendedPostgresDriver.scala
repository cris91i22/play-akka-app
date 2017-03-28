package dl.utils

import com.github.tminglei.slickpg._
import slick.profile.Capability
import slick.driver.JdbcProfile

trait ExtendedPostgresDriver extends ExPostgresDriver with PgHStoreSupport {
  // Add back `capabilities.insertOrUpdate` to enable native `upsert` support; for postgres 9.5+
  override protected def computeCapabilities: Set[Capability] =
    super.computeCapabilities + JdbcProfile.capabilities.insertOrUpdate

  override val api = ExtendedAPI

  object ExtendedAPI extends API with HStoreImplicits
}

object ExtendedPostgresDriver extends ExtendedPostgresDriver