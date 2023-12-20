package com.book.hub.service

import cats.effect.Sync
import cats.implicits._
import com.book.hub.model.BookId
import com.book.hub.model.api.Book
import com.book.hub.model.db.BookDb
import com.book.hub.repository.BookRepository
import io.scalaland.chimney.dsl.TransformationOps
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.util.UUID

trait BookService[F[_]] {
  def create(book: Book): F[Unit]

}

class BookServiceImpl[F[_]: Sync](repository: BookRepository[F]) extends BookService[F] {
  private val logger = Slf4jLogger.getLogger[F]

  override def create(book: Book): F[Unit] =
    for {
      _ <- logger.info("service: create a book")
      bookToCreate = book
                       .into[BookDb]
                       .withFieldConst(_.id, BookId(UUID.randomUUID().toString))
                       .transform
      _ <- repository.create(bookToCreate)
    } yield ()

}
