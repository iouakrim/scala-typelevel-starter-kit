package com.book.hub.route

import cats.Monad
import cats.effect.Sync
import cats.syntax.apply._
import com.book.hub.error.AppError
import com.book.hub.error.BadRequestError
import com.book.hub.error.ErrorPayload
import com.book.hub.error.InternalAppError
import com.book.hub.error.NotFoundError
import com.book.hub.error.AppError.MalformedRequest
import com.book.hub.error.AppError.MalformedRequestBody
import com.book.hub.error.AppError.ServerError
import io.circe.syntax.EncoderOps
import io.circe.CursorOp
import io.circe.DecodingFailure
import org.http4s._
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.StructuredLogger

object ErrorHandler {

  def handleError[F[_]: Sync](err: Throwable, logger: StructuredLogger[F], ctx: Map[String, String]): F[Response[F]] = {
    val dsl = Http4sDsl[F]
    import dsl._
    err match {
      case appError: AppError => handleError[F](logger)(appError)
      case err @ InvalidMessageBodyFailure(_, Some(d: DecodingFailure)) =>
        logger.error(ctx, err)("Unable to parse body from request") *>
          BadRequest(
            ErrorPayload(MalformedRequestBody(CursorOp.opsToPath(d.history))).asJson,
            `Content-Type`(MediaType.application.`problem+json`)
          )
      case parsingError: MessageBodyFailure =>
        logger.error(ctx, parsingError)("Unable to parse body from request") *>
          BadRequest(
            ErrorPayload(MalformedRequest).asJson,
            `Content-Type`(MediaType.application.`problem+json`)
          )
      case err =>
        logger.error(ctx, err)(s"Encountered unexpected error") *>
          InternalServerError(ErrorPayload(ServerError(err)), `Content-Type`(MediaType.application.`problem+json`))
    }
  }

  private def handleError[F[_]: Monad](logger: Logger[F])(appError: AppError): F[Response[F]] = {
    val dsl = Http4sDsl[F]
    import dsl._

    appError match {
      case error: NotFoundError =>
        logger.info(error.details) *>
          NotFound(ErrorPayload(error), `Content-Type`(MediaType.application.`problem+json`))

      case error: ServerError =>
        logger.error(error.cause)(error.details) *>
          InternalServerError(ErrorPayload(error).asJson, `Content-Type`(MediaType.application.`problem+json`))

      case error: InternalAppError =>
        logger.warn(error.details) *>
          InternalServerError(ErrorPayload(error).asJson, `Content-Type`(MediaType.application.`problem+json`))

      case error: BadRequestError =>
        logger.warn(error.details) *>
          BadRequest(ErrorPayload(error).asJson, `Content-Type`(MediaType.application.`problem+json`))

    }
  }
}
