package io.circe.benchmarks

import play.api.libs.json.Json

trait VersionSpecificDecodingSpec { self: DecodingBenchmarkSpec =>
  import benchmark._

  "The 2.12 decoding benchmark" should "correctly decode integers using Play JSON" in {
    assert(decodeIntsP === ints)
  }

  it should "correctly decode case classes using Play JSON" in {
    assert(decodeFoosP === foos)
  }
}

trait VersionSpecificEncodingSpec { self: EncodingBenchmarkSpec =>
  import benchmark._

  "The 2.12 encoding benchmark" should "correctly encode integers using Play JSON" in {
    assert(self.decodeInts(Json.prettyPrint(encodeIntsP)) === Some(ints))
  }

  it should "correctly encode case classes using Play JSON" in {
    assert(self.decodeFoos(Json.prettyPrint(encodeFoosP)) === Some(foos))
  }
}

trait VersionSpecificParsingSpec { self: ParsingBenchmarkSpec =>
  import benchmark._

  "The 2.12 parsing benchmark" should "correctly parse integers using Play JSON" in {
    assert(parseIntsP === intsP)
  }

  it should "correctly parse case classes using Play JSON" in {
    assert(parseFoosP === foosP)
  }
}

trait VersionSpecificPrintingSpec { self: PrintingBenchmarkSpec =>
  import benchmark._

  "The 2.12 printing benchmark" should "correctly print integers using Play JSON" in {
    assert(self.decodeInts(printIntsP) === Some(ints))
  }

  it should "correctly print case classes using Play JSON" in {
    assert(self.decodeFoos(printFoosP) === Some(foos))
  }
}
