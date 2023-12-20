package com.book.hub.model.api

import io.circe.generic.semiauto.deriveDecoder
import io.circe.generic.semiauto.deriveEncoder
import io.circe.Decoder
import io.circe.Encoder

final case class Book(
    title: String,
    author: String
)
object Book {
  implicit val encoder: Encoder.AsObject[Book] = deriveEncoder[Book]
  implicit val decoder: Decoder[Book]          = deriveDecoder[Book]
}
