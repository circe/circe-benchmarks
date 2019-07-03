package io.circe.benchmarks

import argonaut._, Argonaut._
import org.openjdk.jmh.annotations._

trait ArgonautFooInstances {
  implicit val argonautCodecFoo: CodecJson[Foo] = CodecJson(
    {
      case Foo(s, d, i, l, bs) =>
        ("s" := s) ->: ("d" := d) ->: ("i" := i) ->: ("l" := l) ->: ("bs" := bs) ->: jEmptyObject
    },
    c => for {
      s  <- (c --\ "s").as[String]
      d  <- (c --\ "d").as[Double]
      i  <- (c --\ "i").as[Int]
      l  <- (c --\ "l").as[Long]
      bs <- (c --\ "bs").as[List[Boolean]]
    } yield Foo(s, d, i, l, bs)
  )
}

trait ArgonautData { self: ExampleData =>
  val foosA: Json = EncodeJson.of[Map[String, Foo]].encode(foos)
  val intsA: Json = EncodeJson.of[List[Int]].encode(ints)
}

trait ArgonautWriting { self: ExampleData =>
  @Benchmark
  def writeFoosArgonaut: String = EncodeJson.of[Map[String, Foo]].encode(foos).nospaces

  @Benchmark
  def writeIntsArgonaut: String = EncodeJson.of[List[Int]].encode(ints).nospaces
}

trait ArgonautReading { self: ExampleData =>
  @Benchmark
  def readFoosArgonaut: Either[Either[String, (String, CursorHistory)], Map[String, Foo]] = Parse.decode[Map[String, Foo]](foosJson)

  @Benchmark
  def readIntsArgonaut: Either[Either[String, (String, CursorHistory)], List[Int]] = Parse.decode[List[Int]](intsJson)
}

trait ArgonautEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosArgonaut: Json = EncodeJson.of[Map[String, Foo]].encode(foos)

  @Benchmark
  def encodeIntsArgonaut: Json = EncodeJson.of[List[Int]].encode(ints)
}

trait ArgonautDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosArgonaut: DecodeResult[Map[String, Foo]] = foosA.as[Map[String, Foo]]

  @Benchmark
  def decodeIntsArgonaut: DecodeResult[List[Int]] = intsA.as[List[Int]]
}

trait ArgonautPrinting { self: ExampleData =>
  @Benchmark
  def printFoosArgonaut: String = foosA.nospaces

  @Benchmark
  def printIntsArgonaut: String = intsA.nospaces
}

trait ArgonautParsing { self: ExampleData =>
  @Benchmark
  def parseFoosArgonaut: Either[String, Json] = Parse.parse(foosJson)

  @Benchmark
  def parseIntsArgonaut: Either[String, Json] = Parse.parse(intsJson)
}
