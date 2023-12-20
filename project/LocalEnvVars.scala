object LocalEnvVars {

  object RunVars {
    val vars: Map[String, String] = Map(
      "HOST"              -> "localhost",
      "DATABASE_URL"      -> "jdbc:postgresql://localhost:5432/hubBook",
      "DATABASE_USERNAME" -> "postgres",
      "DATABASE_PASSWORD" -> "postgres"
    )
  }

  object TestVars {

    val vars: Seq[String] = Seq(
      "HOST=127.0.0.1"
    ).map(v => s"-D$v")
  }

  object IntegrationTestVars {

    val vars: Seq[String] = Seq(
      "HOST=127.0.0.1"
    ).map(v => s"-D$v")
  }
}
