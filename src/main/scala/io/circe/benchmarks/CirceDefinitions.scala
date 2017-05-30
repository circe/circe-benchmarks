package io.circe.benchmarks

import io.circe._, io.circe.jawn.parse
import org.openjdk.jmh.annotations._

trait CirceFooInstances {
  implicit val circeEncodeFoo: Encoder[Foo] = io.circe.derivation.deriveEncoder
  implicit val circeDecodeFoo: Decoder[Foo] = io.circe.derivation.deriveDecoder
}

trait CirceData { self: ExampleData =>
  @inline def encodeC[A](a: A)(implicit encode: Encoder[A]): Json = encode(a)

  val foosC: Json = encodeC(foos)
  val intsC: Json = encodeC(ints)
}

trait CirceEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosCirce: Json = encodeC(foos)

  @Benchmark
  def encodeIntsCirce: Json = encodeC(ints)
}

trait CirceDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosCirce: Map[String, Foo] = foosC.as[Map[String, Foo]].right.getOrElse(throw new Exception)

  @Benchmark
  def decodeIntsCirce: List[Int] = intsC.as[List[Int]].right.getOrElse(throw new Exception)
}

trait CircePrinting { self: ExampleData =>
  @Benchmark
  def printFoosCirce: String = foosC.noSpaces

  @Benchmark
  def printIntsCirce: String = intsC.noSpaces
}

trait CirceParsing { self: ExampleData =>
  @Benchmark
  def parseFoosCirce: Json = parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsCirce: Json = parse(intsJson).right.getOrElse(throw new Exception)
}
