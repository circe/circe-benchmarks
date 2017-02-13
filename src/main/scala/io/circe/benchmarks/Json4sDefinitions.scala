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

trait Json4sEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoos4s: JValue = encode4s(foos)

  @Benchmark
  def encodeInts4s: JValue = encode4s(ints)
}

trait Json4sDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoos4s: Map[String, Foo] = foos4s.extract[Map[String, Foo]]

  @Benchmark
  def decodeInts4s: List[Int] = ints4s.extract[List[Int]]
}

trait Json4sPrinting { self: ExampleData =>
  @Benchmark
  def printFoos4s: String = JsonMethods.compact(foos4s)

  @Benchmark
  def printInts4s: String = JsonMethods.compact(ints4s)
}

trait Json4sParsing { self: ExampleData =>
  @Benchmark
  def parseFoos4s: JValue = JsonMethods.parse(foosJson)

  @Benchmark
  def parseInts4s: JValue = JsonMethods.parse(intsJson)
}
