package io.circe.benchmarks

import io.circe.{ Decoder, Encoder, HCursor, Json }, io.circe.syntax._

trait CirceFooInstances {
  implicit val circeEncodeFoo: Encoder[Foo] = new Encoder[Foo] {
    def apply(foo: Foo): Json = Json.obj(
      "s" -> foo.s.asJson,
      "d" -> foo.d.asJson,
      "i" -> foo.i.asJson,
      "l" -> foo.l.asJson,
      "bs" -> foo.bs.asJson
    )
  }

  implicit val circeDecodeFoo: Decoder[Foo] = new Decoder[Foo] {
    def apply(c: HCursor): Decoder.Result[Foo] = for {
      s <- c.get[String]("s").right
      d <- c.get[Double]("d").right
      i <- c.get[Int]("i").right
      l <- c.get[Long]("l").right
      bs <- c.get[List[Boolean]]("bs").right
    } yield Foo(s, d, i, l, bs)
  }
}
