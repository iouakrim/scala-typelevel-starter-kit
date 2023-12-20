import LocalEnvVars.RunVars

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.book.hub"
ThisBuild / organizationName := "bookHub"

lazy val root = (project in file("."))
  .settings(
    name                := "scala-typelevel-starter-kit",
    reStart / mainClass := Some("com.book.hub.Main"),
    reStart / envVars   := RunVars.vars,
    libraryDependencies ++= Dependencies.common ++ Dependencies.logging
  )
