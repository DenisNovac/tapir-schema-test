package com.github.denisnovac.multiplenames.v1

import sttp.tapir._
import sttp.tapir.json.circe._

class Endpoints {

  private val baseEndpoint: Endpoint[Unit, Unit, Unit, Any] =
    endpoint
      .in("api" / "v1")

  val postAnimal: Endpoint[Animal, Unit, Animal, Any] =
    baseEndpoint.post
      .in("animal")
      .in(jsonBody[Animal])
      .out(jsonBody[Animal])

}
