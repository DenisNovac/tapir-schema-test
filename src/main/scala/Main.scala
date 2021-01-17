import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.HttpApp
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext

object Main extends IOApp {

  private val ec = ExecutionContext.global

  private val controller: Controller[IO] = new Controller[IO](new Endpoints)

  private val app: HttpApp[IO] = Router("/" -> controller.routes).orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder
      .apply[IO](ec)
      .bindHttp(port = 8080, host = "127.0.0.1")
      .withHttpApp(app)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)

}
