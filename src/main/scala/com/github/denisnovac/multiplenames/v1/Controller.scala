package com.github.denisnovac.multiplenames.v1

import cats.effect.{Concurrent, ContextShift, Sync, Timer}
import cats.implicits._
import org.http4s.HttpRoutes
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter
import sttp.tapir.swagger.http4s.SwaggerHttp4s

class Controller[F[_]: ContextShift: Sync: Concurrent: Timer](endpoints: Endpoints) {

  private val logic: Seq[ServerEndpoint[Animal, Unit, Animal, Any, F]] =
    Seq(endpoints.postAnimal)
      .map(_.serverLogic(mirrorLogic))

  private def mirrorLogic[T](t: T): F[Either[Unit, T]] = Sync[F].delay(t.asRight[Unit])

  private val yaml: String = OpenAPIDocsInterpreter().toOpenAPI(logic.map(_.endpoint), "Test v1", "0.1").toYaml

  private val swagger: HttpRoutes[F] = new SwaggerHttp4s(yaml, contextPath = List("v1", "docs")).routes[F]

  def routes: HttpRoutes[F] = Http4sServerInterpreter().toRoutes(logic.toList) <+> swagger

}
