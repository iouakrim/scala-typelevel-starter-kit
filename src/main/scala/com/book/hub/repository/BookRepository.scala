package com.book.hub.repository

import cats.effect.Sync
import cats.effect.kernel.Resource
import doobie.hikari.HikariTransactor
import com.book.hub.model.BookId
import com.book.hub.model.db.BookDb
import doobie.implicits._
trait BookRepository[F[_]] {
  def create(book: BookDb): F[Int]
  def update(book: BookDb): F[Int]
  def getById(id: BookId): F[BookDb]
  def delete(id: BookId): F[Int]
  def list(): F[List[BookDb]]
}

class BookRepositoryImpl[F[_]: Sync](transactor: Resource[F, HikariTransactor[F]]) extends BookRepository[F] {
  override def create(book: BookDb): F[Int] = transactor.use(xa => BookQueries.insert(book).transact(xa))

  override def getById(id: BookId): F[BookDb] = transactor.use(xa => BookQueries.getById(id).transact(xa))

  override def list(): F[List[BookDb]] = transactor.use(xa => BookQueries.getAll.transact(xa))

  override def delete(id: BookId): F[Int] = transactor.use(xa => BookQueries.delete(id).transact(xa))

  override def update(book: BookDb): F[Int] = transactor.use(xa => BookQueries.update(book).transact(xa))
}
