package io.circe.benchmarks

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._

class ExampleData extends ArgonautData with CirceData with SprayData with PlayData with Json4sData {
  lazy val ints: List[Int] = (0 to 1000).toList

  lazy val foos: Map[String, Foo] = List
    .tabulate(100) { i =>
      ("b" * i) -> Foo("a" * i, (i + 2.0) / (i + 1.0), i, i * 1000L, (0 to i).map(_ % 2 == 0).toList)
    }
    .toMap

  val intsJson: String = intsC.noSpaces
  val foosJson: String = foosC.noSpaces
}

/**
 * Compare the performance of writing operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.CirceOnlyWritingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class CirceOnlyWritingBenchmark extends ExampleData with CirceWriting

/**
 * Compare the performance of reading operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.CirceOnlyReadingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class CirceOnlyReadingBenchmark extends ExampleData with CirceReading

/**
 * Compare the performance of writing operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.WritingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class WritingBenchmark
    extends ExampleData
    with ArgonautWriting
    with CirceWriting
    with SprayWriting
    with PlayWriting
    with Json4sWriting

/**
 * Compare the performance of reading operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.ReadingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class ReadingBenchmark
    extends ExampleData
    with ArgonautReading
    with CirceReading
    with SprayReading
    with PlayReading
    with Json4sReading

/**
 * Compare the performance of encoding operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.EncodingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class EncodingBenchmark
    extends ExampleData
    with ArgonautEncoding
    with CirceEncoding
    with SprayEncoding
    with PlayEncoding
    with Json4sEncoding

/**
 * Compare the performance of decoding operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.DecodingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class DecodingBenchmark
    extends ExampleData
    with ArgonautDecoding
    with CirceDecoding
    with SprayDecoding
    with PlayDecoding
    with Json4sDecoding

/**
 * Compare the performance of printing operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.PrintingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class PrintingBenchmark
    extends ExampleData
    with ArgonautPrinting
    with CircePrinting
    with SprayPrinting
    with PlayPrinting
    with Json4sPrinting

/**
 * Compare the performance of parsing operations.
 *
 * The following command will run the benchmarks with reasonable settings:
 *
 * > sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.ParsingBenchmark"
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class ParsingBenchmark
    extends ExampleData
    with ArgonautParsing
    with CirceParsing
    with SprayParsing
    with PlayParsing
    with Json4sParsing
