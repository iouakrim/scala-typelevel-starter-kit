package com.book.hub.error

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class ErrorPayload(`type`: String, title: String, details: String)

object ErrorPayload {
  def apply(error: AppError): ErrorPayload                     = ErrorPayload(error.`type`, error.title, error.details)
  implicit val ErrorPayloadCodec: Codec.AsObject[ErrorPayload] = deriveCodec
}
