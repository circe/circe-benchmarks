package io.circe.benchmarks

import org.scalatest.FlatSpec

class ParsingBenchmarkSpec extends FlatSpec {
  val benchmark: ParsingBenchmark = new ParsingBenchmark

  import benchmark._

  "The parsing benchmark" should "correctly parse integers using Circe" in {
    assert(parseIntsCirce === intsC)
  }

  it should "correctly parse integers using Circe Jackson" in {
    assert(parseIntsCirceJackson === intsC)
  }

  it should "correctly parse integers using Argonaut" in {
    assert(parseIntsArgonaut === intsA)
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

  it should "correctly parse integers using Jackson" in {
    assert(parseIntsJackson === intsJackson)
  }

  it should "correctly parse case classes using Circe" in {
    assert(parseFoosCirce === foosC)
  }

  it should "correctly parse case classes using Circe Jackson" in {
    assert(parseFoosCirceJackson === foosC)
  }

  it should "correctly parse case classes using Argonaut" in {
    assert(parseFoosArgonaut === foosA)
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

  it should "correctly parse case classes using Jackson" in {
    /**
     * A workaround for the fact that I don't remember how `JsonNode` equality
     * works in Jackson.
     */
    assert(mapper.writeValueAsString(parseFoosJackson) === mapper.writeValueAsString(foosJackson))
  }
}
