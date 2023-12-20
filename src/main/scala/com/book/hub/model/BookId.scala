package com.book.hub.model

import cats.Show
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class BookId(value: String)
object BookId {
  implicit val codec: Codec.AsObject[BookId] = deriveCodec[BookId]
  implicit val show: Show[BookId]            = _.value
}
