import sbt._

object Dependencies {

  object Versions {
    val catsEffect = "3.4.8"
    val http4s     = "0.23.24"
    val log4cats   = "2.6.0"
    val logback    = "1.4.7"
    val pureconfig = "0.17.4"
    val circe      = "0.14.6"
    val doobie     = "1.0.0-RC4"
    val flyway     = "9.22.3"
    val chimney    = "0.8.4"
    val macwire    = "2.5.9"

  }

  object Compile {
    lazy val catsEffect           = "org.typelevel"         %% "cats-effect"            % Versions.catsEffect
    lazy val http4sDsl            = "org.http4s"            %% "http4s-dsl"             % Versions.http4s
    lazy val http4sEmberServer    = "org.http4s"            %% "http4s-ember-server"    % Versions.http4s
    lazy val http4sCirce          = "org.http4s"            %% "http4s-circe"           % Versions.http4s
    lazy val log4catsCore         = "org.typelevel"         %% "log4cats-core"          % Versions.log4cats
    lazy val log4catsSlf4j        = "org.typelevel"         %% "log4cats-slf4j"         % Versions.log4cats
    lazy val logbackClassic       = "ch.qos.logback"         % "logback-classic"        % Versions.logback
    lazy val pureconfig           = "com.github.pureconfig" %% "pureconfig"             % Versions.pureconfig
    lazy val pureconfigCatsEffect = "com.github.pureconfig" %% "pureconfig-cats-effect" % Versions.pureconfig
    lazy val circeCore            = "io.circe"              %% "circe-core"             % Versions.circe
    lazy val circeGeneric         = "io.circe"              %% "circe-generic"          % Versions.circe
    lazy val doobieCore           = "org.tpolecat"          %% "doobie-core"            % Versions.doobie
    lazy val doobieH2             = "org.tpolecat"          %% "doobie-h2"              % Versions.doobie
    lazy val doobieHikari         = "org.tpolecat"          %% "doobie-hikari"          % Versions.doobie
    lazy val doobiePostgres       = "org.tpolecat"          %% "doobie-postgres"        % Versions.doobie
    lazy val flyway               = "org.flywaydb"           % "flyway-core"            % Versions.flyway
    lazy val chimney              = "io.scalaland"          %% "chimney"                % Versions.chimney
    lazy val macwireMacros = "com.softwaremill.macwire" %% "macros" % Versions.macwire % "provided"
    lazy val macwireUtil   = "com.softwaremill.macwire" %% "util"   % Versions.macwire

  }

  import Compile._
  val logging = Seq(log4catsCore, log4catsSlf4j, logbackClassic)
  val common = Seq(
    catsEffect,
    http4sDsl,
    http4sCirce,
    http4sEmberServer,
    pureconfig,
    pureconfigCatsEffect,
    circeCore,
    circeGeneric,
    doobieCore,
    doobieH2,
    doobieHikari,
    doobiePostgres,
    flyway,
    chimney,
    macwireMacros,
    macwireUtil
  )

}
