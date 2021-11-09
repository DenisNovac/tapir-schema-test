import cats.effect.{ConcurrentEffect, ContextShift, IO, Timer}
import io.circe.parser

val json =
  """
    |{
    |  "nodes": [
    |    {
    |      "value": "string",
    |      "type": "TermNode"
    |    },
    |    {
    |      "field": "title",
    |      "node": {
    |        "value": "asd",
    |        "type": "TermNode"
    |      },
    |      "type": "FieldNode"
    |    }
    |  ],
    |  "type": "OpNode"
    |}
    |""".stripMargin

// it works
parser.parse(json).toOption.get.as[QueryNode]
