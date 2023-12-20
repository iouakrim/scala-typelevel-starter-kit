package com.book.hub

import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.Resource
import cats.effect.Sync
import com.book.hub.model.AppConfig
import com.book.hub.model.DbConfig
import com.book.hub.repository.BookRepositoryImpl
import com.book.hub.route.BookRoutes
import com.book.hub.service.BookServiceImpl
import com.comcast.ip4s.Host
import com.comcast.ip4s.IpLiteralSyntax
import com.comcast.ip4s.Port
import com.zaxxer.hikari.HikariConfig
import doobie.hikari.HikariTransactor
import org.flywaydb.core.Flyway
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import pureconfig.generic.auto._
import pureconfig.module.catseffect.loadConfigF
object Main extends IOApp {

  private def buildTransactor(dbConfig: DbConfig): Resource[IO, HikariTransactor[IO]] =
    for {
      hikariConfig <- Resource.pure {
                        val config = new HikariConfig()
                        config.setDriverClassName(dbConfig.driver)
                        config.setJdbcUrl(dbConfig.url)
                        config.setUsername(dbConfig.username)
                        config.setPassword(dbConfig.password)
                        config.setMaximumPoolSize(dbConfig.poolSize)
                        config
                      }
      xa <- HikariTransactor.fromHikariConfig[IO](hikariConfig)
    } yield xa

  private def initFlyway[F[_]: Sync](dbConfig: DbConfig): F[Int] = Sync[F].delay {
    Flyway
      .configure()
      .dataSource(dbConfig.url, dbConfig.username, dbConfig.password)
      .baselineOnMigrate(true)
      .load()
      .migrate()
      .migrationsExecuted
  }
  override def run(args: List[String]): IO[ExitCode] = {
    val app: IO[ExitCode] = for {
      config <- loadConfigF[IO, AppConfig]
      _      <- initFlyway[IO](config.dbConfig)
      transactor = buildTransactor(config.dbConfig)
      repository = new BookRepositoryImpl[IO](transactor)
      service    = new BookServiceImpl[IO](repository)
      routes     = new BookRoutes[IO](service)
      router = Router(
                 "/books" -> routes.bookRoutes
               ).orNotFound
      app <- EmberServerBuilder
               .default[IO]
               .withHost(Host.fromString(config.httpConfig.host).getOrElse(ipv4"0.0.0.0"))
               .withPort(Port.fromInt(config.httpConfig.port).getOrElse(port"8080"))
               .withHttpApp(router)
               .build
               .use(_ => IO.never)
    } yield app
    app.as(ExitCode.Success)
  }
}
