import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import sttp.tapir.Schema
import sttp.tapir.generic.{Configuration => TapirConfiguration}
import io.circe.generic.extras.Configuration

sealed trait Tree

case class StringLeaf(string: String) extends Tree

case object EndLeaf extends Tree

case class Node(left: Tree, right: Tree) extends Tree

case class EndNode(left: EndLeaf.type, right: EndLeaf.type) extends Tree

object Tree {

  implicit private val circeConfigWithDiscriminator: Configuration =
    Configuration.default.withDefaults.withDiscriminator("type")

  implicit private val tapirConfigWithDiscriminator: TapirConfiguration =
    TapirConfiguration.default.withDiscriminator("type")

  implicit lazy val codec: Codec[Tree] = deriveConfiguredCodec[Tree]
  implicit lazy val schema: Schema[Tree] = Schema.derived[Tree]
}