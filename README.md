# Scala Typelevel Starter Kit

The Kit is a toolkit designed to help developers kickstart Scala projects with the Typelevel stack. It includes a carefully selected set of dependencies, simplifying the development process and ensuring a strong foundation for building reliable and scalable applications.

## Dependencies

The project incorporates the following key dependencies:

- **cats-effect:** Asynchronous programming in a purely functional style. [Documentation](https://typelevel.org/cats-effect/)
- **http4s-dsl:** HTTP DSL for building concise and expressive HTTP endpoints.[Documentation](https://http4s.org/)
- **http4s-ember-server:** A lightweight HTTP server built on top of http4s.
- **http4s-circe:** Integration of Circe with http4s for seamless JSON support.
- **log4cats-core:** Typelevel logging library for Cats.[Documentation](https://typelevel.org/log4cats/)
- **log4cats-slf4j:** SLF4J integration for log4cats.
- **logback-classic:** Logging implementation for SLF4J.
- **pureconfig:** A library for loading configuration files in a type-safe manner.[Documentation](https://pureconfig.github.io/)
- **pureconfig-cats-effect:** Cats-effect integration for pureconfig.
- **circe-core:** Core functionality for Circe JSON library.[Documentation](https://circe.github.io/circe/)
- **circe-generic:** Automatic derivation of codecs for case classes in Circe.
- **doobie-core:** Pure functional JDBC layer for Scala. [Documentation](https://tpolecat.github.io/doobie/)
- **doobie-h2:** H2 driver for doobie.
- **doobie-hikari:** HikariCP integration for doobie.
- **doobie-postgres:** PostgreSQL driver for doobie.
- **flyway-core:** Database migration tool for evolving databases along with your codebase.[Documentation](https://flywaydb.org/documentation/)
- **chimney:** Scala library for boilerplate-free, type-safe data transformations.[Documentation](https://scalalandio.github.io/chimney/)

## SBT Plugins

The Scala Typelevel Starter Kit utilizes the following SBT plugins:

- **sbt-scalafmt:** Scala code formatting. [Documentation](https://scalameta.org/scalafmt/)
- **sbt-revolver:** Continuous compilation and automatic application reloading. [Documentation](https://github.com/spray/sbt-revolver)
