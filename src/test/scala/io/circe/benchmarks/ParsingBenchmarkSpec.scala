package io.circe.benchmarks

import org.scalatest.FlatSpec

class ParsingBenchmarkSpec extends FlatSpec with VersionSpecificParsingSpec {
  val benchmark: ParsingBenchmark = new ParsingBenchmark

  import benchmark._

  "The parsing benchmark" should "correctly parse integers using Circe" in {
    assert(parseIntsCirce === intsC)
  }

  it should "correctly parse integers using Argonaut" in {
    assert(parseIntsArgonaut === intsA)
  }

  it should "correctly parse integers using Spray JSON" in {
    assert(parseIntsSpray === intsS)
  }

  it should "correctly parse case classes using Circe" in {
    assert(parseFoosCirce === foosC)
  }

  it should "correctly parse case classes using Argonaut" in {
    assert(parseFoosArgonaut === foosA)
  }

  it should "correctly parse case classes using Spray JSON" in {
    assert(parseFoosSpray === foosS)
  }
}
