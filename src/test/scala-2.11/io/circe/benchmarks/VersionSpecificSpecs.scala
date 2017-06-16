package io.circe.benchmarks

import io.github.netvl.picopickle.backends.jawn.JsonPickler._
import play.api.libs.json.Json

trait VersionSpecificWritingSpec { self: WritingBenchmarkSpec =>
  import benchmark._

  "The 2.11 writing benchmark" should "correctly write integers using Picopickle" in {
    assert(decodeInts(writeIntsPico) === Some(ints))
  }
  
  it should "correctly write integers using Play JSON" in {
    assert(decodeInts(writeIntsPlay) === Some(ints))
  }

  // TODO: Figure out why this is failing.
  ignore should "correctly write case classes using Picopickle" in {
    assert(decodeInts(writeFoosPico) === Some(foos))
  }

  it should "correctly write case classes using Play JSON" in {
    assert(decodeFoos(writeFoosPlay) === Some(foos))
  }
}

trait VersionSpecificReadingSpec { self: ReadingBenchmarkSpec =>
  import benchmark._

  "The 2.11 reading benchmark" should "correctly read integers using Picopickle" in {
    assert(readIntsPico === ints)
  }

  it should "correctly read integers using Play JSON" in {
    assert(readIntsPlay === ints)
  }
  
  it should "correctly read case classes using Picopickle" in {
    assert(readFoosPico === foos)
  }

  it should "correctly read case classes using Play JSON" in {
    assert(readFoosPlay === foos)
  }
}

trait VersionSpecificDecodingSpec { self: DecodingBenchmarkSpec =>
  import benchmark._

  "The 2.11 decoding benchmark" should "correctly decode integers using Picopickle" in {
    assert(decodeIntsPico === ints)
  }

  it should "correctly decode integers using Play JSON" in {
    assert(decodeIntsPlay === ints)
  }

  it should "correctly decode case classes using Picopickle" in {
    assert(decodeFoosPico === foos)
  }

  it should "correctly decode case classes using Play JSON" in {
    assert(decodeFoosPlay === foos)
  }
}

trait VersionSpecificEncodingSpec { self: EncodingBenchmarkSpec =>
  import benchmark._

  "The 2.11 encoding benchmark" should "correctly encode integers using Picopickle" in {
    assert(self.decodeInts(writeAst(encodeIntsPico)) === Some(ints))
  }

  it should "correctly encode integers using Play JSON" in {
    assert(self.decodeInts(Json.prettyPrint(encodeIntsPlay)) === Some(ints))
  }

  it should "correctly encode case classes using Picopickle" in {
    assert(self.decodeFoos(writeAst(encodeFoosPico)) === Some(foos))
  }

  it should "correctly encode case classes using Play JSON" in {
    assert(self.decodeFoos(Json.prettyPrint(encodeFoosPlay)) === Some(foos))
  }
}

trait VersionSpecificParsingSpec { self: ParsingBenchmarkSpec =>
  import benchmark._

  "The 2.11 parsing benchmark" should "correctly parse integers using Picopickle" in {
    assert(parseIntsPico === intsPico)
  }

  it should "correctly parse integers using Play JSON" in {
    assert(parseIntsPlay === intsP)
  }

  it should "correctly parse case classes using Picopickle" in {
    assert(parseFoosPico === foosPico)
  }

  it should "correctly parse case classes using Play JSON" in {
    assert(parseFoosPlay === foosP)
  }
}

trait VersionSpecificPrintingSpec { self: PrintingBenchmarkSpec =>
  import benchmark._

  "The 2.11 printing benchmark" should "correctly print integers using Picopickle" in {
    assert(self.decodeInts(printIntsPico) === Some(ints))
  }

  it should "correctly print integers using Play JSON" in {
    assert(self.decodeInts(printIntsPlay) === Some(ints))
  }
  
  it should "correctly print case classes using Picopickle" in {
    assert(self.decodeFoos(printFoosPico) === Some(foos))
  }

  it should "correctly print case classes using Play JSON" in {
    assert(self.decodeFoos(printFoosPlay) === Some(foos))
  }
}
