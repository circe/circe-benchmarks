package io.circe.benchmarks

import org.scalatest.flatspec.AnyFlatSpec

class DecodingBenchmarkSpec extends AnyFlatSpec {
  val benchmark: DecodingBenchmark = new DecodingBenchmark

  import benchmark._

  "The decoding benchmark" should "correctly decode integers using Circe" in {
    assert(decodeIntsCirce === Right(ints))
  }

  it should "correctly decode integers using Argonaut" in {
    assert(decodeIntsArgonaut.result === Right(ints))
  }

  it should "correctly decode integers using Spray JSON" in {
    assert(decodeIntsSpray === ints)
  }

  it should "correctly decode integers using Json4s" in {
    assert(decodeIntsJson4s === ints)
  }

  it should "correctly decode integers using Play JSON" in {
    assert(decodeIntsPlay === ints)
  }

  it should "correctly decode case classes using Circe" in {
    assert(decodeFoosCirce === Right(foos))
  }

  it should "correctly decode case classes using Argonaut" in {
    assert(decodeFoosArgonaut.result === Right(foos))
  }

  it should "correctly decode case classes using Spray JSON" in {
    assert(decodeFoosSpray === foos)
  }

  it should "correctly decode case classes using Json4s" in {
    assert(decodeFoosJson4s === foos)
  }

  it should "correctly decode case classes using Play JSON" in {
    assert(decodeFoosPlay === foos)
  }
}
