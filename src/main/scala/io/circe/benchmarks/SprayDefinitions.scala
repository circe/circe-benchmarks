package io.circe.benchmarks

import org.openjdk.jmh.annotations._
import spray.json._
import spray.json.DefaultJsonProtocol._

trait SprayFooInstances {
  implicit val sprayFormatFoo: RootJsonFormat[Foo] = new RootJsonFormat[Foo] {
    def write(foo: Foo): JsObject = JsObject(
      "s" -> foo.s.toJson,
      "d" -> foo.d.toJson,
      "i" -> foo.i.toJson,
      "l" -> foo.l.toJson,
      "bs" -> foo.bs.toJson
    )

    def read(value: JsValue): Foo = value.asJsObject.getFields("s", "d", "i", "l", "bs") match {
      case Seq(JsString(s), d, i, l, bs) =>
        Foo(s, d.convertTo[Double], i.convertTo[Int], l.convertTo[Long], bs.convertTo[List[Boolean]])
      case _ => throw new DeserializationException("Foo expected")
    }
  }
}

trait SprayData { self: ExampleData =>
  @inline def encodeS[A](a: A)(implicit encode: JsonWriter[A]): JsValue = encode.write(a)

  val foosS: JsValue = encodeS(foos)
  val intsS: JsValue = encodeS(ints)
}

trait SprayWriting { self: ExampleData =>
  @Benchmark
  def writeFoosSpray: String = encodeS(foos).compactPrint

  @Benchmark
  def writeIntsSpray: String = encodeS(ints).compactPrint
}

trait SprayReading { self: ExampleData =>
  @Benchmark
  def readFoosSpray: Map[String, Foo] = JsonParser(foosJson).convertTo[Map[String, Foo]]

  @Benchmark
  def readIntsSpray: List[Int] = JsonParser(intsJson).convertTo[List[Int]]
}

trait SprayEncoding { self: ExampleData =>
  @Benchmark
  def encodeFoosSpray: JsValue = encodeS(foos)

  @Benchmark
  def encodeIntsSpray: JsValue = encodeS(ints)
}

trait SprayDecoding { self: ExampleData =>
  @Benchmark
  def decodeFoosSpray: Map[String, Foo] = foosS.convertTo[Map[String, Foo]]

  @Benchmark
  def decodeIntsSpray: List[Int] = intsS.convertTo[List[Int]]
}

trait SprayPrinting { self: ExampleData =>
  @Benchmark
  def printFoosSpray: String = foosS.compactPrint

  @Benchmark
  def printIntsSpray: String = intsS.compactPrint
}

trait SprayParsing { self: ExampleData =>
  @Benchmark
  def parseFoosSpray: JsValue = JsonParser(foosJson)

  @Benchmark
  def parseIntsSpray: JsValue = JsonParser(intsJson)
}
