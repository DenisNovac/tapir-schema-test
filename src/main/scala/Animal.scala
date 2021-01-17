import io.circe.Codec
import io.circe.generic.AutoDerivation
import io.circe.generic.extras.semiauto.deriveConfiguredCodec
import io.circe.generic.extras.{Configuration => CirceConfiguration}
import sttp.tapir.Schema
import sttp.tapir.generic.{SchemaDerivation, Configuration => TapirConfiguration}

sealed trait Animal {
  def name: String
}

sealed trait Pet extends Animal {
  def name: String
  def favToy: String
}

object Animal extends AutoDerivation with SchemaDerivation {

  implicit val customConfig: CirceConfiguration =
    CirceConfiguration.default.withDefaults.withDiscriminator("type")

  implicit val codec: Codec[Animal] = deriveConfiguredCodec

  implicit val tapirConfig: TapirConfiguration = TapirConfiguration.default.withDiscriminator("type")

  // It can't be implicit since recursive derivation fails
  val schema: Schema[Animal] = Schema.derived

  case class Tiger(name: String)                                                      extends Animal
  case class Elephant(name: String)                                                   extends Animal
  case class Dog(name: String, favToy: String, training: Boolean, friends: List[Pet]) extends Pet
  case class Cat(name: String, favToy: String, friends: List[Pet])                    extends Pet

  /* Works */

  //case class WildCat(name: String, friends: List[Animal])                             extends Animal

  /* Fails */

  // this is copy of Cat class but still fails
  //case class WildCat(name: String, favToy: String, friends: List[Pet]) extends Pet

  // this one is the most useful i guess
  case class WildCat(name: String, favToy: String, friends: List[Animal]) extends Pet

  //case class WildCat(name: String, friends: List[Pet]) extends Animal
  //case class WildCat(name: String, favToy: String, street: String, friends: List[Pet]) extends Pet
  //case class WildCat(name: String, favToy: String, friends: List[Pet], street: String) extends Pet

}
