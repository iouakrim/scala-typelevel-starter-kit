package com.book.hub.repository

import cats.effect.Sync
import cats.effect.kernel.Resource
import cats.implicits.catsSyntaxApplicativeError
import doobie.hikari.HikariTransactor
import cats.implicits._
import com.book.hub.model.db.BookDb
import com.book.hub.repository.BookQueries.insert
import doobie.implicits._
import org.typelevel.log4cats.slf4j.Slf4jLogger
trait BookRepository[F[_]] {
  def create(book: BookDb): F[Unit]
}

class BookRepositoryImpl[F[_]: Sync](transactor: Resource[F, HikariTransactor[F]]) extends BookRepository[F] {
  private val logger = Slf4jLogger.getLogger[F]
  override def create(book: BookDb): F[Unit] =
    transactor.use { xa =>
      for {
        _ <- logger.info("insert book into db")
        _ <- insert(book).run.transact(xa).attempt
        _ <- logger.info("book inserted")
      } yield ()
    }

}
