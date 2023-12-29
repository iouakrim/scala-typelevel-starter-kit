package com.book.hub.repository

import com.book.hub.model.BookId
import com.book.hub.model.db.BookDb
import doobie.implicits.toSqlInterpolator

object BookQueries {

  def getAll: doobie.ConnectionIO[List[BookDb]] = sql"SELECT id, title, author FROM book".query[BookDb].to[List]
  def getById(id: BookId): doobie.ConnectionIO[BookDb] =
    sql"SELECT id, title, author FROM book WHERE id = ${id.value}".query[BookDb].unique

  def insert(book: BookDb): doobie.ConnectionIO[Int] =
    sql"INSERT INTO book (id, title, author) VALUES (${book.id}, ${book.title}, ${book.author})".update.run
  def update(book: BookDb): doobie.ConnectionIO[Int] =
    sql"UPDATE book SET title = ${book.title}, author = ${book.author} WHERE id = ${book.id}".update.run

  def delete(id: BookId): doobie.ConnectionIO[Int] = sql"DELETE FROM book WHERE id = $id".update.run
}
