package io.circe.benchmarks

import io.circe._, io.circe.jawn.parse, io.circe.syntax._
import org.openjdk.jmh.annotations._

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

trait CirceData { self: ExampleData =>
  @inline def encodeC[A](a: A)(implicit encode: Encoder[A]): Json = encode(a)

  val foosC: Json = encodeC(foos)
  val intsC: Json = encodeC(ints)
}

trait CirceEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosC: Json = encodeC(foos)

  @Benchmark
  def encodeIntsC: Json = encodeC(ints)
}

trait CirceDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosC: Map[String, Foo] = foosC.as[Map[String, Foo]].right.getOrElse(throw new Exception)

  @Benchmark
  def decodeIntsC: List[Int] = intsC.as[List[Int]].right.getOrElse(throw new Exception)
}

trait CircePrinting { self: ExampleData =>
  @Benchmark
  def printFoosC: String = foosC.noSpaces

  @Benchmark
  def printIntsC: String = intsC.noSpaces
}

trait CirceParsing { self: ExampleData =>
  @Benchmark
  def parseFoosC: Json = parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsC: Json = parse(intsJson).right.getOrElse(throw new Exception)
}
