package io.circe.benchmarks

import org.scalatest.FlatSpec

class DecodingBenchmarkSpec extends FlatSpec {
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

  it should "correctly decode integers using Json4s" in {
    assert(decodeIntsJson4s === ints)
  }

  it should "correctly decode integers using Jackson" in {
    assert(decodeIntsJackson === ints)
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

  it should "correctly decode case classes using Json4s" in {
    assert(decodeFoosJson4s === foos)
  }

  it should "correctly decode case classes using Jackson" in {
    assert(decodeFoosJackson === foos)
  }
}
