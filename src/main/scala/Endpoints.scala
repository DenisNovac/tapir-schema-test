import sttp.tapir._
import sttp.tapir.json.circe._

class Endpoints {

  private val baseEndpoint: Endpoint[Unit, Unit, Unit, Any] =
    endpoint
      .in("api" / "v1")

  val postAnimal: Endpoint[QueryNode, Unit, QueryNode, Any] =
    baseEndpoint.post
      .in("node")
      .in(jsonBody[QueryNode])
      .out(jsonBody[QueryNode])

}
