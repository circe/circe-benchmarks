package io.circe.benchmarks

import io.circe._, io.circe.jackson, io.circe.jawn.{ decode, parse }
import org.openjdk.jmh.annotations._

trait CirceData { self: ExampleData =>
  @inline def encodeC[A](a: A)(implicit encode: Encoder[A]): Json = encode(a)

  val foosC: Json = encodeC(foos)
  val intsC: Json = encodeC(ints)
}

trait CirceWriting { self: ExampleData =>
  @Benchmark
  def writeFoosCirce: String = encodeC(foos).noSpaces

  @Benchmark
  def writeIntsCirce: String = encodeC(ints).noSpaces
}

trait CirceReading { self: ExampleData =>
  @Benchmark
  def readFoosCirce: Map[String, Foo] = decode[Map[String, Foo]](foosJson).right.get

  @Benchmark
  def readIntsCirce: List[Int] = decode[List[Int]](intsJson).right.get
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

  @Benchmark
  def printFoosCirceJackson: String = jackson.jacksonPrint(foosC)

  @Benchmark
  def printIntsCirceJackson: String = jackson.jacksonPrint(intsC)
}

trait CirceParsing { self: ExampleData =>
  @Benchmark
  def parseFoosCirce: Json = parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsCirce: Json = parse(intsJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseFoosCirceJackson: Json = jackson.parse(foosJson).right.getOrElse(throw new Exception)

  @Benchmark
  def parseIntsCirceJackson: Json = jackson.parse(intsJson).right.getOrElse(throw new Exception)
}
