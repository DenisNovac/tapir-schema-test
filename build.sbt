name := "tapir-test"

version := "0.1"

scalaVersion := "2.13.4"

val tapirVersion = "0.18.3"

libraryDependencies += "org.typelevel" %% "cats-core"            % "2.3.1"
libraryDependencies += "org.typelevel" %% "cats-effect"          % "2.3.1"
libraryDependencies += "io.circe"      %% "circe-core"           % "0.13.0"
libraryDependencies += "io.circe"      %% "circe-generic"        % "0.13.0"
libraryDependencies += "io.circe"      %% "circe-generic-extras" % "0.13.0"

libraryDependencies += "org.http4s" %% "http4s-core"         % "0.21.15"
libraryDependencies += "org.http4s" %% "http4s-server"       % "0.21.15"
libraryDependencies += "org.http4s" %% "http4s-blaze-core"   % "0.21.15"
libraryDependencies += "org.http4s" %% "http4s-blaze-server" % "0.21.15"

val tapirDeps = Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-core",
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server",
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe",
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs",
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml",
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s"
)

libraryDependencies ++= tapirDeps.map(_ % tapirVersion)
