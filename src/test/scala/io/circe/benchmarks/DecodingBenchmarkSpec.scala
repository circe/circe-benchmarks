package io.circe.benchmarks

import org.scalatest.FlatSpec

class DecodingBenchmarkSpec extends FlatSpec with VersionSpecificDecodingSpec {
  val benchmark: DecodingBenchmark = new DecodingBenchmark

  import benchmark._

  "The decoding benchmark" should "correctly decode integers using Circe" in {
    assert(decodeIntsCirce === ints)
  }

  it should "correctly decode integers using Argonaut" in {
    assert(decodeIntsArgonaut === ints)
  }

  it should "correctly decode integers using Spray JSON" in {
    assert(decodeIntsSpray === ints)
  }

  it should "correctly decode case classes using Circe" in {
    assert(decodeFoosCirce === foos)
  }

  it should "correctly decode case classes using Argonaut" in {
    assert(decodeFoosArgonaut === foos)
  }

  it should "correctly decode case classes using Spray JSON" in {
    assert(decodeFoosSpray === foos)
  }
}
