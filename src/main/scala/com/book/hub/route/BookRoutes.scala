package com.book.hub.route

import cats.effect.Async
import cats.implicits.catsSyntaxApplicativeError
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.book.hub.model.BookId
import com.book.hub.model.api.Book
import com.book.hub.model.db.BookDb
import com.book.hub.service.BookService
import io.scalaland.chimney.dsl.TransformationOps
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.slf4j.Slf4jLogger
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder

import java.util.UUID

class BookRoutes[F[_]: Async](service: BookService[F]) extends Http4sDsl[F] {
  private val logger = Slf4jLogger.getLogger[F]

  val bookRoutes: HttpRoutes[F] =
    HttpRoutes
      .of[F] {
        case req @ POST -> Root =>
          val app = for {
            _       <- logger.info(s"create book")
            bookApi <- req.as[Book]
            bookToCreate = bookApi.into[BookDb].withFieldConst(_.id, UUID.randomUUID().toString).transform
            _   <- service.create(bookToCreate)
            res <- Created(bookToCreate)
          } yield res
          app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))

        case _ @GET -> Root / id =>
          val app = for {
            _       <- logger.info(s"get book by id : $id")
            bookApi <- service.get(BookId(id))
            res     <- Ok(bookApi)
          } yield res
          app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))

        case _ @GET -> Root =>
          val app = for {
            _     <- logger.info("get list of books")
            books <- service.list()
            res   <- Ok(books)
          } yield res
          app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))

        case req @ PUT -> Root / id =>
          val app = for {
            _       <- logger.info(s"update book having id: $id ")
            bookApi <- req.as[Book]
            bookToUpdate = bookApi.into[BookDb].withFieldConst(_.id, id).transform
            _   <- service.update(bookToUpdate)
            res <- Ok(bookToUpdate)
          } yield res
          app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))

        case _ @DELETE -> Root / id =>
          val app = for {
            _   <- logger.info(s"delete book having id: $id ")
            _   <- service.delete(BookId(id))
            res <- Ok()
          } yield res
          app.recoverWith(ErrorHandler.handleError(_, logger, Map.empty))
      }
}
