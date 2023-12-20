package com.book.hub.error

sealed trait AppError extends Throwable with Product with Serializable {
  val `type`: String
  val title: String
  val details: String
}

sealed trait NotFoundError   extends AppError
sealed trait BadRequestError extends AppError

sealed trait InternalAppError extends AppError

object AppError {

  final case class MalformedRequestBody(message: String) extends BadRequestError {
    override val `type`: String  = "ERROR-0001"
    override val title: String   = "Consumer detail is malformed."
    override val details: String = s"Malformed or missing field $message"
  }

  final case object MalformedRequest extends BadRequestError {
    override val `type`: String  = "ERROR-0002"
    override val title: String   = "Malformed request"
    override val details: String = "Request is malformed."
  }

  final case class ServerError(cause: Throwable) extends InternalAppError {
    override val `type`: String  = "ERROR-0003"
    override val title: String   = "Unexpected error"
    override val details: String = s"An unexpected error occurred: ${cause.getMessage}"
  }

}
