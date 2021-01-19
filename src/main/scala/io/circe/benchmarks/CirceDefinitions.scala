package io.circe.benchmarks

import io.circe._, io.circe.jackson, io.circe.jawn.{ decode, parse }
import org.openjdk.jmh.annotations._

trait CirceFooInstances {
  implicit val circeEncodeFoo: Encoder[Foo] = io.circe.derivation.deriveEncoder
  implicit val circeDecodeFoo: Decoder[Foo] = io.circe.derivation.deriveDecoder
}

trait CirceData { self: ExampleData =>
  val foosC: Json = Encoder[Map[String, Foo]].apply(foos)
  val intsC: Json = Encoder[List[Int]].apply(ints)
}

trait CirceWriting { self: ExampleData =>
  @Benchmark
  def writeFoosCirce: String = CircePrinting.p.print(Encoder[Map[String, Foo]].apply(foos))

  @Benchmark
  def writeIntsCirce: String = CircePrinting.p.print(Encoder[List[Int]].apply(ints))
}

trait CirceReading { self: ExampleData =>
  @Benchmark
  def readFoosCirce: Either[Error, Map[String, Foo]] = decode[Map[String, Foo]](foosJson)

  @Benchmark
  def readIntsCirce: Either[Error, List[Int]] = decode[List[Int]](intsJson)
}

trait CirceEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosCirce: Json = Encoder[Map[String, Foo]].apply(foos)

  @Benchmark
  def encodeIntsCirce: Json = Encoder[List[Int]].apply(ints)
}

trait CirceDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosCirce: Decoder.Result[Map[String, Foo]] = foosC.as[Map[String, Foo]]

  @Benchmark
  def decodeIntsCirce: Decoder.Result[List[Int]] = intsC.as[List[Int]]
}

object CircePrinting {
  val p = io.circe.Printer.noSpaces.copy(reuseWriters = true)
}

trait CircePrinting { self: ExampleData =>
  @Benchmark
  def printFoosCirce: String = CircePrinting.p.print(foosC)

  @Benchmark
  def printIntsCirce: String = CircePrinting.p.print(intsC)

  @Benchmark
  def printFoosCirceJackson: String = jackson.jacksonPrint(foosC)

  @Benchmark
  def printIntsCirceJackson: String = jackson.jacksonPrint(intsC)
}

trait CirceParsing { self: ExampleData =>
  @Benchmark
  def parseFoosCirce: Either[ParsingFailure, Json] = parse(foosJson)

  @Benchmark
  def parseIntsCirce: Either[ParsingFailure, Json] = parse(intsJson)

  @Benchmark
  def parseFoosCirceJackson: Either[ParsingFailure, Json] = jackson.parse(foosJson)

  @Benchmark
  def parseIntsCirceJackson: Either[ParsingFailure, Json] = jackson.parse(intsJson)
}
