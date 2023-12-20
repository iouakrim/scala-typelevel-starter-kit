package com.book.hub.route

import cats.effect.Async
import cats.implicits.catsSyntaxApplicativeError
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.book.hub.model.api.Book
import com.book.hub.service.BookService
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.slf4j.Slf4jLogger
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder

class BookRoutes[F[_]: Async](service: BookService[F]) extends Http4sDsl[F] {
  private val logger = Slf4jLogger.getLogger[F]

  val bookRoutes: HttpRoutes[F] =
    HttpRoutes
      .of[F] { case req @ POST -> Root =>
        val app = for {
          _       <- logger.info(s"create book")
          bookApi <- req.as[Book]
          _       <- service.create(bookApi)
          res     <- Ok(bookApi)
        } yield res
        app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))
      }
}
