import io.circe.Codec
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import sttp.tapir.Schema
import sttp.tapir.generic.{Configuration => TapirConfiguration}

sealed trait QueryNode

case class TermNode(value: String) extends QueryNode

case class FieldNode(field: String, node: QueryNode) extends QueryNode

case class OpNode(nodes: List[QueryNode]) extends QueryNode

object QueryNode {

  implicit val customConfig: Configuration     = Configuration.default.withDiscriminator("type")
  implicit val tapirConfig: TapirConfiguration = TapirConfiguration.default.withDiscriminator("type")

  implicit def codec: Codec[QueryNode]   = deriveConfiguredCodec[QueryNode]
  implicit def schema: Schema[QueryNode] = Schema.derived[QueryNode]

}
