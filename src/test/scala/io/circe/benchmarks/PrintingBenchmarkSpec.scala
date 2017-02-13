package io.circe.benchmarks

import argonaut.Parse, argonaut.Argonaut._
import org.scalatest.FlatSpec

class PrintingBenchmarkSpec extends FlatSpec with VersionSpecificPrintingSpec {
  val benchmark: PrintingBenchmark = new PrintingBenchmark

  import benchmark._

  def decodeInts(json: String): Option[List[Int]] =
    Parse.decodeOption[List[Int]](json)

  def decodeFoos(json: String): Option[Map[String, Foo]] =
    Parse.decodeOption[Map[String, Foo]](json)

  "The printing benchmark" should "correctly print integers using Circe" in {
    assert(decodeInts(printIntsC) === Some(ints))
  }

  it should "correctly print integers using Argonaut" in {
    assert(decodeInts(printIntsA) === Some(ints))
  }

  it should "correctly print integers using Spray JSON" in {
    assert(decodeInts(printIntsS) === Some(ints))
  }

  it should "correctly print case classes using Circe" in {
    assert(decodeFoos(printFoosC) === Some(foos))
  }

  it should "correctly print case classes using Argonaut" in {
    assert(decodeFoos(printFoosA) === Some(foos))
  }

  it should "correctly print case classes using Spray JSON" in {
    assert(decodeFoos(printFoosS) === Some(foos))
  }

  it should "correnctly print case classes using Json4s" in {
    assert(decodeFoos(printFoos4s) === Some(foos))
  }
}
