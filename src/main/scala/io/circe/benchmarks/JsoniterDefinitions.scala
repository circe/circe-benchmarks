package io.circe.benchmarks

import org.openjdk.jmh.annotations._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._
import io.circe.benchmarks.Codecs._

trait JsoniterScalaFooInstances {
  implicit val jsoniterCodec: JsonValueCodec[Foo] = JsonCodecMaker.make[Foo]
}

object Codecs {

  implicit val jsoniterMapCodec: JsonValueCodec[Map[String, Foo]] = JsonCodecMaker.make[Map[String, Foo]]
  implicit val jsoniterListCodec = JsonCodecMaker.make[List[Foo]]
  implicit val jsoniterListIntCodec = JsonCodecMaker.make[List[Int]]

}

trait JsoniterData { self: ExampleData =>
  @inline def encodeJ[A](a: A)(implicit encode: JsonValueCodec[A]): String =
    writeToString(a)(encode)

  @inline def decodeJ[A](a: String)(implicit encode: JsonValueCodec[A]): A =
    readFromString(a)(encode)

  val foosJ: String = encodeJ(foos)
  val intsJ: String = encodeJ(ints)
}

trait JsoniterWriting { self: ExampleData =>
  @Benchmark
  def writeFoosJsoniter: String = encodeJ(foos)

  @Benchmark
  def writeIntsJsoniter: String = encodeJ(ints)
}

trait JsoniterReading { self: ExampleData =>
  @Benchmark
  def readFoosJsoniter: Map[String, Foo] = readFromString[Map[String, Foo]](foosJson)

  @Benchmark
  def readIntsJsoniter: List[Int] = readFromString[List[Int]](intsJson)
}

trait JsoniterEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosJsoniter: String = encodeJ(foos)

  @Benchmark
  def encodeIntsJsoniter: String = encodeJ(ints)
}

trait JsoniterDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosJsoniter: Map[String, Foo] = decodeJ[Map[String, Foo]](foosJ)

  @Benchmark
  def decodeIntsJsoniter: List[Int] = decodeJ[List[Int]](intsJ)
}

trait JsoniterPrinting { self: ExampleData =>
  @Benchmark
  def printFoosJsoniter: String = encodeJ(decodeJ[Map[String, Foo]](foosJ))

  @Benchmark
  def printIntsJsoniter: String = encodeJ(decodeJ[List[Int]](intsJ))
}

trait JsoniterParsing { self: ExampleData =>
  @Benchmark
  def parseFoosJsoniter: Map[String, Foo] = decodeJ[Map[String, Foo]](foosJson)

  @Benchmark
  def parseIntsJsoniter: List[Int] = decodeJ[List[Int]](intsJson)
}
