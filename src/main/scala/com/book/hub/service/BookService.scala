package com.book.hub.service

import cats.effect.Sync
import cats.implicits._
import com.book.hub.model.BookId
import com.book.hub.model.db.BookDb
import com.book.hub.repository.BookRepository
import org.typelevel.log4cats.slf4j.Slf4jLogger

trait BookService[F[_]] {
  def create(book: BookDb): F[Unit]
  def update(book: BookDb): F[Unit]
  def get(id: BookId): F[BookDb]
  def delete(id: BookId): F[Unit]
  def list(): F[List[BookDb]]

}

class BookServiceImpl[F[_]: Sync](repository: BookRepository[F]) extends BookService[F] {
  private val logger = Slf4jLogger.getLogger[F]

  override def create(book: BookDb): F[Unit] = repository.create(book).map(_ => ())

  override def get(id: BookId): F[BookDb] = repository.getById(id)

  override def list(): F[List[BookDb]] = repository.list()

  override def delete(id: BookId): F[Unit] = repository.delete(id).map(_ => ())

  override def update(book: BookDb): F[Unit] = repository.update(book).map(_ => ())
}
