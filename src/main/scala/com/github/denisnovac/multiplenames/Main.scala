package com.github.denisnovac.multiplenames

import cats.effect.{ExitCode, IO, IOApp}
import com.github.denisnovac.multiplenames.v1.{Controller, Endpoints}
import com.github.denisnovac.multiplenames.v2.{Controller => ControllerV2, Endpoints => EndpointsV2}
import cats.implicits._

import org.http4s.HttpApp
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import org.http4s.server.Router

import scala.concurrent.ExecutionContext

object Main extends IOApp {

  private val ec = ExecutionContext.global

  private val controllerV1: Controller[IO]   = new Controller[IO](new Endpoints)
  private val controllerV2: ControllerV2[IO] = new ControllerV2[IO](new EndpointsV2)

  private val app: HttpApp[IO] = Router("/" -> (controllerV1.routes <+> controllerV2.routes)).orNotFound

  //127.0.0.1:8080/v1/docs
  //127.0.0.1:8080/v2/docs
  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder
      .apply[IO](ec)
      .bindHttp(port = 8080, host = "127.0.0.1")
      .withHttpApp(app)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)

}
