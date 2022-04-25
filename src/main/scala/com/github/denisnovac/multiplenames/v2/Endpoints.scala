package com.github.denisnovac.multiplenames.v2

import sttp.tapir._
import sttp.tapir.json.circe._

class Endpoints {

  private val baseEndpoint: Endpoint[Unit, Unit, Unit, Any] =
    endpoint
      .in("api" / "v2")

  val postAnimal: Endpoint[Animal, Unit, Animal, Any] =
    baseEndpoint.post
      .in("animal")
      .in(jsonBody[Animal])
      .out(jsonBody[Animal])

}
