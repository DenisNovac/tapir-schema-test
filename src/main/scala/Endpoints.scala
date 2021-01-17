import sttp.tapir._
import sttp.tapir.json.circe._

class Endpoints {

  /* You won't see Tapir's discriminator without this. But json with "type" will work fine since it uses Circe's
   * discriminator while decoding */
  //implicit val animalS: Schema[Animal] = Animal.schema

  private val baseEndpoint: Endpoint[Unit, Unit, Unit, Any] =
    endpoint
      .in("api" / "v1")

  val postAnimal: Endpoint[Animal, Unit, Animal, Any] =
    baseEndpoint.post
      .in("animal")
      .in(jsonBody[Animal])
      .out(jsonBody[Animal])

}
