package io.circe.benchmarks

import play.api.libs.json.Json

trait VersionSpecificDecodingSpec { self: DecodingBenchmarkSpec =>
  import benchmark._

  "The 2.12 decoding benchmark" should "correctly decode integers using Play JSON" in {
    assert(decodeIntsPlay === ints)
  }

  it should "correctly decode case classes using Play JSON" in {
    assert(decodeFoosPlay === foos)
  }
}

trait VersionSpecificEncodingSpec { self: EncodingBenchmarkSpec =>
  import benchmark._

  "The 2.12 encoding benchmark" should "correctly encode integers using Play JSON" in {
    assert(self.decodeInts(Json.prettyPrint(encodeIntsPlay)) === Some(ints))
  }

  it should "correctly encode case classes using Play JSON" in {
    assert(self.decodeFoos(Json.prettyPrint(encodeFoosPlay)) === Some(foos))
  }
}

trait VersionSpecificParsingSpec { self: ParsingBenchmarkSpec =>
  import benchmark._

  "The 2.12 parsing benchmark" should "correctly parse integers using Play JSON" in {
    assert(parseIntsPlay === intsP)
  }

  it should "correctly parse case classes using Play JSON" in {
    assert(parseFoosPlay === foosP)
  }
}

trait VersionSpecificPrintingSpec { self: PrintingBenchmarkSpec =>
  import benchmark._

  "The 2.12 printing benchmark" should "correctly print integers using Play JSON" in {
    assert(self.decodeInts(printIntsPlay) === Some(ints))
  }

  it should "correctly print case classes using Play JSON" in {
    assert(self.decodeFoos(printFoosPlay) === Some(foos))
  }
}
