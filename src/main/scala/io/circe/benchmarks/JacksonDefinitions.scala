package io.circe.benchmarks

import com.fasterxml.jackson.databind.{ JsonNode, ObjectMapper }
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.openjdk.jmh.annotations._

trait JacksonData { self: ExampleData =>
  val mapper: ObjectMapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  @inline def encodeJackson[T](value: T): String = mapper.writeValueAsString(value)

  val foosJackson: String = encodeJackson(foos)
  val intsJackson: String = encodeJackson(ints)
}

trait JacksonEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosJackson: String = encodeJackson(foos)

  @Benchmark
  def encodeIntsJackson: String = encodeJackson(ints)
}

trait JacksonDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosJackson: Map[String, Foo] = mapper.readValue(foosJackson, classOf[Map[String, Foo]])

  @Benchmark
  def decodeIntsJackson: List[Int] = mapper.readValue(intsJackson, classOf[List[Int]])
}

trait JacksonPrinting { self: ExampleData =>
  @Benchmark
  def printFoosJackson: String = foosJackson.toString()

  @Benchmark
  def printIntsJackson: String = intsJackson.toString()
}

trait JacksonParsing { self: ExampleData =>
  @Benchmark
  def parseFoosJackson: JsonNode = mapper.readTree(foosJson)

  @Benchmark
  def parseIntsJackson: JsonNode = mapper.readTree(intsJson)
}
