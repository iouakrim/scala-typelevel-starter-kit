package com.book.hub.model.db

import com.book.hub.model.BookId
import io.circe.generic.semiauto.deriveDecoder
import io.circe.generic.semiauto.deriveEncoder
import io.circe.Decoder
import io.circe.Encoder

final case class BookDb(
    id: BookId,
    title: String,
    author: String
)
object BookDb {
  implicit val encoder: Encoder.AsObject[BookDb] = deriveEncoder[BookDb]
  implicit val decoder: Decoder[BookDb]          = deriveDecoder[BookDb]
}
