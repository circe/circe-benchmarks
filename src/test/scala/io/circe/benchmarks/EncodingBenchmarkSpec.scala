package io.circe.benchmarks

import argonaut.Parse
import argonaut.Argonaut._
import org.json4s.jackson.JsonMethods
import org.scalatest.FlatSpec
import play.api.libs.json.Json

class EncodingBenchmarkSpec extends FlatSpec {
  val benchmark: EncodingBenchmark = new EncodingBenchmark

  import benchmark._

  def decodeInts(json: String): Option[List[Int]] =
    Parse.decodeOption[List[Int]](json)

  def decodeFoos(json: String): Option[Map[String, Foo]] =
    Parse.decodeOption[Map[String, Foo]](json)

  "The encoding benchmark" should "correctly encode integers using Circe" in {
    assert(decodeInts(encodeIntsCirce.noSpaces) === Some(ints))
  }

  it should "correctly encode integers using Argonaut" in {
    assert(decodeInts(encodeIntsArgonaut.nospaces) === Some(ints))
  }

  it should "correctly encode integers using Spray JSON" in {
    assert(decodeInts(encodeIntsSpray.compactPrint) === Some(ints))
  }

  it should "correctly encode integers using Json4s" in {
    assert(decodeInts(JsonMethods.compact(encodeIntsJson4s)) === Some(ints))
  }

  it should "correctly encode integers using Play JSON" in {
    assert(decodeInts(Json.prettyPrint(encodeIntsPlay)) === Some(ints))
  }

  it should "correctly encode case classes using Circe" in {
    assert(decodeFoos(encodeFoosCirce.noSpaces) === Some(foos))
  }

  it should "correctly encode case classes using Argonaut" in {
    assert(decodeFoos(encodeFoosArgonaut.nospaces) === Some(foos))
  }

  it should "correctly encode case classes using Spray JSON" in {
    assert(decodeFoos(encodeFoosSpray.compactPrint) === Some(foos))
  }

  it should "correctly encode case classes using Json4s" in {
    assert(decodeFoos(JsonMethods.compact(encodeFoosJson4s)) === Some(foos))
  }

  it should "correctly encode case classes using Play JSON" in {
    assert(decodeFoos(Json.prettyPrint(encodeFoosPlay)) === Some(foos))
  }
}
