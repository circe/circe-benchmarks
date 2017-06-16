package io.circe.benchmarks

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.{ JsonNode, ObjectMapper }
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.openjdk.jmh.annotations._

trait JacksonData { self: ExampleData =>
  val mapper: ObjectMapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  @inline def encodeJackson[T](value: T): JsonNode = mapper.valueToTree(value)

  val foosJackson: JsonNode = encodeJackson(foos)
  val intsJackson: JsonNode = encodeJackson(ints)
}

trait JacksonWriting { self: ExampleData =>
  @Benchmark
  def writeFoosJackson: String = mapper.writeValueAsString(foos)

  @Benchmark
  def writeIntsJackson: String = mapper.writeValueAsString(ints)
}

trait JacksonReading { self: ExampleData =>
  @Benchmark
  def readFoosJackson: Map[String, Foo] = mapper.readValue(foosJson, new TypeReference[Map[String, Foo]] {})

  @Benchmark
  def readIntsJackson: List[Int] = mapper.readValue(intsJson, classOf[List[Int]])
}

trait JacksonEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosJackson: JsonNode = encodeJackson(foos)

  @Benchmark
  def encodeIntsJackson: JsonNode = encodeJackson(ints)
}

trait JacksonDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosJackson: Map[String, Foo] = mapper.convertValue(
    foosJackson,
    new TypeReference[Map[String, Foo]] {}
  )

  @Benchmark
  def decodeIntsJackson: List[Int] = mapper.treeToValue(intsJackson, classOf[List[Int]])
}

trait JacksonPrinting { self: ExampleData =>
  @Benchmark
  def printFoosJackson: String = mapper.writeValueAsString(foosJackson)

  @Benchmark
  def printIntsJackson: String = mapper.writeValueAsString(intsJackson)
}

trait JacksonParsing { self: ExampleData =>
  @Benchmark
  def parseFoosJackson: JsonNode = mapper.readTree(foosJson)

  @Benchmark
  def parseIntsJackson: JsonNode = mapper.readTree(intsJson)
}
