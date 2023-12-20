package com.book.hub.repository

import com.book.hub.model.BookId
import com.book.hub.model.api.Book
import com.book.hub.model.db.BookDb
import doobie.Query0
import doobie.implicits.toSqlInterpolator

object BookQueries {
  def getByIdQuery(id: BookId): Query0[Book] =
    sql"SELECT id, title, author FROM books WHERE id = ${id.value}".query[Book]

  def insert(book: BookDb): doobie.Update0 =
    sql"""
         |INSERT INTO Book (
         |  id,
         |  title,
         |  author
         |)
         |VALUES (
         |  ${book.id},
         |  ${book.title},
         |  ${book.author}
         |)
        """.stripMargin.update

}
