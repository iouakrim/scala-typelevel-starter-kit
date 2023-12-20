package com.book.hub.model

final case class AppConfig(httpConfig: HttpConfig, dbConfig: DbConfig)
final case class HttpConfig(host: String, port: Int, logLevel: String)
case class DbConfig(driver: String, url: String, username: String, password: String, poolSize: Int)
