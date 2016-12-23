package io.circe.benchmarks

import argonaut.Parse, argonaut.Argonaut._
import org.scalatest.FlatSpec

class EncodingBenchmarkSpec extends FlatSpec with VersionSpecificEncodingSpec {
  val benchmark: EncodingBenchmark = new EncodingBenchmark

  import benchmark._

  def decodeInts(json: String): Option[List[Int]] =
    Parse.decodeOption[List[Int]](json)

  def decodeFoos(json: String): Option[Map[String, Foo]] =
    Parse.decodeOption[Map[String, Foo]](json)

  "The encoding benchmark" should "correctly encode integers using Circe" in {
    assert(decodeInts(encodeIntsC.noSpaces) === Some(ints))
  }

  it should "correctly encode integers using Argonaut" in {
    assert(decodeInts(encodeIntsA.nospaces) === Some(ints))
  }

  it should "correctly encode integers using Spray JSON" in {
    assert(decodeInts(encodeIntsS.compactPrint) === Some(ints))
  }

  it should "correctly encode case classes using Circe" in {
    assert(decodeFoos(encodeFoosC.noSpaces) === Some(foos))
  }

  it should "correctly encode case classes using Argonaut" in {
    assert(decodeFoos(encodeFoosA.nospaces) === Some(foos))
  }

  it should "correctly encode case classes using Spray JSON" in {
    assert(decodeFoos(encodeFoosS.compactPrint) === Some(foos))
  }
}
