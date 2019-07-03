package io.circe.benchmarks

import org.json4s._
import org.json4s.jackson.JsonMethods
import org.openjdk.jmh.annotations._

trait Json4sData extends DefaultWriters { self: ExampleData =>

  implicit val formats = DefaultFormats

  @inline def encode4s[T](value: T): JValue = Extraction.decompose(value)

  val foos4s: JValue = encode4s(foos)
  val ints4s: JValue = encode4s(ints)
}

trait Json4sWriting { self: ExampleData =>
  @Benchmark
  def writeFoosJson4s: String = JsonMethods.compact(Extraction.decompose(foos))

  @Benchmark
  def writeIntsJson4s: String = JsonMethods.compact(Extraction.decompose(ints))
}

trait Json4sReading { self: ExampleData =>
  @Benchmark
  def readFoosJson4s: Map[String, Foo] = JsonMethods.parse(foosJson).extract[Map[String, Foo]]

  @Benchmark
  def readIntsJson4s: List[Int] = JsonMethods.parse(intsJson).extract[List[Int]]
}

trait Json4sEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosJson4s: JValue = encode4s(foos)

  @Benchmark
  def encodeIntsJson4s: JValue = encode4s(ints)
}

trait Json4sDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosJson4s: Map[String, Foo] = foos4s.extract[Map[String, Foo]]

  @Benchmark
  def decodeIntsJson4s: List[Int] = ints4s.extract[List[Int]]
}

trait Json4sPrinting { self: ExampleData =>
  @Benchmark
  def printFoosJson4s: String = JsonMethods.compact(foos4s)

  @Benchmark
  def printIntsJson4s: String = JsonMethods.compact(ints4s)
}

trait Json4sParsing { self: ExampleData =>
  @Benchmark
  def parseFoosJson4s: JValue = JsonMethods.parse(foosJson)

  @Benchmark
  def parseIntsJson4s: JValue = JsonMethods.parse(intsJson)
}
