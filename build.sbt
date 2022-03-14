ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"


libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.0.0-M1"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.0.0-M1"

libraryDependencies += "io.circe" %% "circe-generic" % "0.14.1"
libraryDependencies += "io.circe" %% "circe-generic-extras" % "0.14.1"


lazy val root = (project in file("."))
  .settings(
    name := "tapir-specific-adt-nodes-test"
  )
