package com.book.hub.model.log

case class LogContext(correlationId: `X-Correlation-ID`) {
  val asMap: Map[String, String] = Map(
    `X-Correlation-ID`.name -> correlationId.value
  )
}

final case class `X-Correlation-ID`(value: String)

object `X-Correlation-ID` {
  val name: String = "X-Correlation-ID"
}
