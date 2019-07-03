package io.circe.benchmarks

import org.scalatest.flatspec.AnyFlatSpec

class ParsingBenchmarkSpec extends AnyFlatSpec {
  val benchmark: ParsingBenchmark = new ParsingBenchmark

  import benchmark._

  "The parsing benchmark" should "correctly parse integers using Circe" in {
    assert(parseIntsCirce === Right(intsC))
  }

  it should "correctly parse integers using Circe Jackson" in {
    assert(parseIntsCirceJackson === Right(intsC))
  }

  it should "correctly parse integers using Argonaut" in {
    assert(parseIntsArgonaut === Right(intsA))
  }

  it should "correctly parse integers using Spray JSON" in {
    assert(parseIntsSpray === intsS)
  }

  it should "correctly parse integers using Json4s" in {
    assert(parseIntsJson4s === ints4s)
  }

  it should "correctly parse integers using Play JSON" in {
    assert(parseIntsPlay === intsP)
  }

  it should "correctly parse case classes using Circe" in {
    assert(parseFoosCirce === Right(foosC))
  }

  it should "correctly parse case classes using Circe Jackson" in {
    assert(parseFoosCirceJackson === Right(foosC))
  }

  it should "correctly parse case classes using Argonaut" in {
    assert(parseFoosArgonaut === Right(foosA))
  }

  it should "correctly parse case classes using Spray JSON" in {
    assert(parseFoosSpray === foosS)
  }

  it should "correctly parse case classes using Json4s" in {
    assert(parseFoosJson4s === foos4s)
  }

  it should "correctly parse case classes using Play JSON" in {
    assert(parseFoosPlay === foosP)
  }
}
