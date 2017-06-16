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
  @inline def encodeA[A](a: A)(implicit encode: EncodeJson[A]): Json = encode(a)

  val foosA: Json = encodeA(foos)
  val intsA: Json = encodeA(ints)
}

trait ArgonautWriting { self: ExampleData =>
  @Benchmark
  def writeFoosArgonaut: String = encodeA(foos).nospaces

  @Benchmark
  def writeIntsArgonaut: String = encodeA(ints).nospaces
}

trait ArgonautReading { self: ExampleData =>
  @Benchmark
  def readFoosArgonaut: Map[String, Foo] = Parse.decodeOption[Map[String, Foo]](foosJson).get

  @Benchmark
  def readIntsArgonaut: List[Int] = Parse.decodeOption[List[Int]](intsJson).get
}

trait ArgonautEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosArgonaut: Json = encodeA(foos)

  @Benchmark
  def encodeIntsArgonaut: Json = encodeA(ints)
}

trait ArgonautDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosArgonaut: Map[String, Foo] = foosA.as[Map[String, Foo]].result.right.getOrElse(throw new Exception)

  @Benchmark
  def decodeIntsArgonaut: List[Int] = intsA.as[List[Int]].result.right.getOrElse(throw new Exception)
}

trait ArgonautPrinting { self: ExampleData =>
  @Benchmark
  def printFoosArgonaut: String = foosA.nospaces

  @Benchmark
  def printIntsArgonaut: String = intsA.nospaces
}

trait ArgonautParsing { self: ExampleData =>
  @Benchmark
  def parseFoosArgonaut: Json = Parse.parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsArgonaut: Json = Parse.parse(intsJson).right.getOrElse(throw new Exception)
}
