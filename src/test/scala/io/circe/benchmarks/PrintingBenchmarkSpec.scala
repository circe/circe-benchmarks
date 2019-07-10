package io.circe.benchmarks

import argonaut.Parse, argonaut.Argonaut._
import org.scalatest.flatspec.AnyFlatSpec

class PrintingBenchmarkSpec extends AnyFlatSpec {
  val benchmark: PrintingBenchmark = new PrintingBenchmark

  import benchmark._

  def decodeInts(json: String): Option[List[Int]] =
    Parse.decodeOption[List[Int]](json)

  def decodeFoos(json: String): Option[Map[String, Foo]] =
    Parse.decodeOption[Map[String, Foo]](json)

  "The printing benchmark" should "correctly print integers using Circe" in {
    assert(decodeInts(printIntsCirce) === Some(ints))
  }

  it should "correctly print integers using Circe Jackson" in {
    assert(decodeInts(printIntsCirceJackson) === Some(ints))
  }

  it should "correctly print integers using Argonaut" in {
    assert(decodeInts(printIntsArgonaut) === Some(ints))
  }

  it should "correctly print integers using Spray JSON" in {
    assert(decodeInts(printIntsSpray) === Some(ints))
  }

  it should "correctly print integers using Json4s" in {
    assert(decodeInts(printIntsJson4s) === Some(ints))
  }

  it should "correctly print integers using Play JSON" in {
    assert(decodeInts(printIntsPlay) === Some(ints))
  }

  it should "correctly print case classes using Circe" in {
    assert(decodeFoos(printFoosCirce) === Some(foos))
  }

  it should "correctly print case classes using Circe Jackson" in {
    assert(decodeFoos(printFoosCirceJackson) === Some(foos))
  }

  it should "correctly print case classes using Argonaut" in {
    assert(decodeFoos(printFoosArgonaut) === Some(foos))
  }

  it should "correctly print case classes using Spray JSON" in {
    assert(decodeFoos(printFoosSpray) === Some(foos))
  }

  it should "correctly print case classes using Json4s" in {
    assert(decodeFoos(printFoosJson4s) === Some(foos))
  }

  it should "correctly print case classes using Play JSON" in {
    assert(decodeFoos(printFoosPlay) === Some(foos))
  }
}
