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

trait ArgonautEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosA: Json = encodeA(foos)

  @Benchmark
  def encodeIntsA: Json = encodeA(ints)
}

trait ArgonautDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosA: Map[String, Foo] = foosA.as[Map[String, Foo]].result.right.getOrElse(throw new Exception)

  @Benchmark
  def decodeIntsA: List[Int] = intsA.as[List[Int]].result.right.getOrElse(throw new Exception)
}

trait ArgonautPrinting { self: ExampleData =>
  @Benchmark
  def printFoosA: String = foosA.nospaces

  @Benchmark
  def printIntsA: String = intsA.nospaces
}

trait ArgonautParsing { self: ExampleData =>
  @Benchmark
  def parseFoosA: Json = Parse.parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsA: Json = Parse.parse(intsJson).right.getOrElse(throw new Exception)
}
