package io.circe.benchmarks

import io.circe.{ Decoder, Encoder }

trait CirceFooInstances {
  implicit val circeEncodeFoo: Encoder[Foo] = io.circe.derivation.deriveEncoder
  implicit val circeDecodeFoo: Decoder[Foo] = io.circe.derivation.deriveDecoder
}
