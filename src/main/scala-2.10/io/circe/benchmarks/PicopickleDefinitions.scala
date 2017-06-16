package io.circe.benchmarks

import io.github.netvl.picopickle.backends.jawn.JsonPickler
import io.github.netvl.picopickle.backends.jawn.JsonPickler._
import org.openjdk.jmh.annotations._

/**
 * Note that this file appears in both the scala-2.10 and scala-2.11 source trees, and any changes
 * should be reflected in both places.
 */
trait PicopickleFooInstances

trait PicopickleData { self: ExampleData =>
  @inline def encodePico[A](a: A)(implicit encode: JsonPickler.Writer[A]): backend.BValue = write(a)(encode)

  val foosPico: backend.BValue = encodePico(foos)
  val intsPico: backend.BValue = encodePico(ints)
}

trait PicopickleWriting { self: ExampleData =>
  @Benchmark
  def writeFoosPico: String = writeAst(encodePico(foos))

  @Benchmark
  def writeIntsPico: String = writeAst(encodePico(ints))
}

trait PicopickleReading { self: ExampleData =>
  @Benchmark
  def readFoosPico: Map[String, Foo] = read[Map[String, Foo]](readAst(foosJson))

  @Benchmark
  def readIntsPico: List[Int] = read[List[Int]](readAst(intsJson))
}

trait PicopickleEncoding { self: ExampleData =>
  @Benchmark
  def encodeIntsPico: backend.BValue = encodePico(ints)

  @Benchmark
  def encodeFoosPico: backend.BValue = encodePico(foos)
}

trait PicopickleDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosPico: Map[String, Foo] = read[Map[String, Foo]](foosPico)

  @Benchmark
  def decodeIntsPico: List[Int] = read[List[Int]](intsPico)
}

trait PicopicklePrinting { self: ExampleData =>
  @Benchmark
  def printFoosPico: String = writeAst(foosPico)

  @Benchmark
  def printIntsPico: String = writeAst(intsPico)
}

trait PicopickleParsing { self: ExampleData =>
  @Benchmark
  def parseFoosPico: backend.BValue = readAst(foosJson)

  @Benchmark
  def parseIntsPico: backend.BValue = readAst(intsJson)
}
