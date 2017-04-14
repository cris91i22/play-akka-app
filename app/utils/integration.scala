package utils

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

package object integration {
  /**
    * Execution context by default using
    * ForkJoinPool
    */
  implicit val defaultExecContext: ExecutionContextExecutor =
    ExecutionContext.fromExecutor(new scala.concurrent.forkjoin.ForkJoinPool)

}
