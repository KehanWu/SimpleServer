package servlet

sealed trait ApiV1ServletError

case class PostgreError(exc: Throwable, details: String) extends ApiV1ServletError {
  override def toString: String = s"Encountered Postgres operation error: ${exc.getMessage}"
}
