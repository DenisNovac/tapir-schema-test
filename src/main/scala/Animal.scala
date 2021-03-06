import io.circe.Codec
import io.circe.generic.AutoDerivation
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import io.circe.generic.extras.{Configuration => CirceConfiguration}
import sttp.tapir.Schema
import sttp.tapir.generic.{SchemaDerivation, Configuration => TapirConfiguration}

sealed trait Animal {
  def name: String
}

object Animal extends AutoDerivation with SchemaDerivation {

  implicit val customConfig: CirceConfiguration =
    CirceConfiguration.default.withDefaults.withDiscriminator("type")

  implicit val codec: Codec[Animal] = deriveConfiguredCodec

  implicit val tapirConfig: TapirConfiguration = TapirConfiguration.default.withDiscriminator("type")

  // It can't be implicit since recursive derivation fails
  implicit val schema: Schema[Animal] = Schema.derived

  case class Dog(name: String, training: Boolean, friends: List[Animal]) extends Animal
  case class Cat(name: String, favToy: String, friends: List[Animal])    extends Animal
}
