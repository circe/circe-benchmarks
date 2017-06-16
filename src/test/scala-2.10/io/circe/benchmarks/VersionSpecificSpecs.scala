package io.circe.benchmarks

import io.github.netvl.picopickle.backends.jawn.JsonPickler._

trait VersionSpecificWritingSpec { self: WritingBenchmarkSpec =>
  import benchmark._

  "The 2.10 writing benchmark" should "correctly write integers using Picopickle" in {
    assert(decodeInts(writeIntsPico) === Some(ints))
  }
  
  it should "correctly write case classes using Picopickle" in {
    assert(decodeFoos(writeFoosPico) === Some(foos))
  }
}

trait VersionSpecificReadingSpec { self: ReadingBenchmarkSpec =>
  import benchmark._

  "The 2.10 reading benchmark" should "correctly read integers using Picopickle" in {
    assert(readFoosPico === foos)
  }
  
  it should "correctly read case classes using Picopickle" in {
    assert(readIntsPico === ints)
  }
}

trait VersionSpecificDecodingSpec { self: DecodingBenchmarkSpec =>
  import benchmark._

  "The 2.10 decoding benchmark" should "correctly decode integers using Picopickle" in {
    assert(decodeIntsPico === ints)
  }

  it should "correctly decode case classes using Picopickle" in {
    assert(decodeFoosPico === foos)
  }
}

trait VersionSpecificEncodingSpec { self: EncodingBenchmarkSpec =>
  import benchmark._

  "The 2.10 encoding benchmark" should "correctly encode integers using Picopickle" in {
    assert(self.decodeInts(writeAst(encodeIntsPico)) === Some(ints))
  }

  it should "correctly encode case classes using Picopickle" in {
    assert(self.decodeFoos(writeAst(encodeFoosPico)) === Some(foos))
  }
}

trait VersionSpecificParsingSpec { self: ParsingBenchmarkSpec =>
  import benchmark._

  "The 2.10 parsing benchmark" should "correctly parse integers using Picopickle" in {
    assert(parseIntsPico === intsPico)
  }

  it should "correctly parse case classes using Picopickle" in {
    assert(parseFoosPico === foosPico)
  }
}

trait VersionSpecificPrintingSpec { self: PrintingBenchmarkSpec =>
  import benchmark._

  "The 2.10 printing benchmark" should "correctly print integers using Picopickle" in {
    assert(self.decodeInts(printIntsPico) === Some(ints))
  }
  
  it should "correctly print case classes using Picopickle" in {
    assert(self.decodeFoos(printFoosPico) === Some(foos))
  }
}
