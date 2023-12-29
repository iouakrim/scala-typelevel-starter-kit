# Scala Typelevel Starter Kit

The **Scala Typelevel Starter Kit** is a comprehensive starting point for developers aiming to leverage the power of the Typelevel stack in Scala. This kit provides a curated set of dependencies to streamline the development process, ensuring a solid foundation for building robust and scalable applications.

## Dependencies

The project incorporates the following key dependencies:

- **cats-effect:** Asynchronous programming in a purely functional style.
- **http4s-dsl:** HTTP DSL for building concise and expressive HTTP endpoints.
- **http4s-ember-server:** A lightweight HTTP server built on top of http4s.
- **http4s-circe:** Integration of Circe with http4s for seamless JSON support.
- **log4cats-core:** Typelevel logging library for Cats.
- **log4cats-slf4j:** SLF4J integration for log4cats.
- **logback-classic:** Logging implementation for SLF4J.
- **pureconfig:** A library for loading configuration files in a type-safe manner.
- **pureconfig-cats-effect:** Cats-effect integration for pureconfig.
- **circe-core:** Core functionality for Circe JSON library.
- **circe-generic:** Automatic derivation of codecs for case classes in Circe.
- **doobie-core:** Pure functional JDBC layer for Scala.
- **doobie-h2:** H2 driver for doobie.
- **doobie-hikari:** HikariCP integration for doobie.
- **doobie-postgres:** PostgreSQL driver for doobie.
- **flyway-core:** Database migration tool for evolving databases along with your codebase.
- **chimney:** Scala library for boilerplate-free, type-safe data transformations.

## Plugins

The project also utilizes the following SBT plugins:

- **sbt-scalafmt:** An SBT plugin for Scala formatting, ensuring a consistent code style.
- **sbt-revolver:** An SBT plugin for fast development turnaround with continuous compilation and automatic reloading of your application.
