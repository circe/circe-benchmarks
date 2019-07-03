package io.circe.benchmarks

import org.scalatest.flatspec.AnyFlatSpec

class ReadingBenchmarkSpec extends AnyFlatSpec {
  val benchmark: ReadingBenchmark = new ReadingBenchmark

  import benchmark._

  "The reading benchmark" should "correctly read integers using Circe" in {
    assert(readIntsCirce === Right(ints))
  }

  it should "correctly read integers using Argonaut" in {
    assert(readIntsArgonaut === Right(ints))
  }

  it should "correctly read integers using Spray JSON" in {
    assert(readIntsSpray === ints)
  }

  it should "correctly read integers using Json4s" in {
    assert(readIntsJson4s === ints)
  }

  it should "correctly read integers using Play JSON" in {
    assert(readIntsPlay === ints)
  }

  it should "correctly read case classes using Circe" in {
    assert(readFoosCirce === Right(foos))
  }

  it should "correctly read case classes using Argonaut" in {
    assert(readFoosArgonaut === Right(foos))
  }

  it should "correctly read case classes using Spray JSON" in {
    assert(readFoosSpray === foos)
  }

  it should "correctly read case classes using Json4s" in {
    assert(readFoosJson4s === foos)
  }

  it should "correctly read case classes using Play JSON" in {
    assert(readFoosPlay === foos)
  }
}
