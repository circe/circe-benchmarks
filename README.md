# circe-benchmarks

[![Build status](https://img.shields.io/travis/circe/circe-benchmarks/master.svg)](https://travis-ci.org/circe/circe-benchmarks)
[![Coverage status](https://img.shields.io/codecov/c/github/circe/circe-benchmarks/master.svg)](https://codecov.io/github/circe/circe-benchmarks)
[![Gitter](https://img.shields.io/badge/gitter-join%20chat-green.svg)](https://gitter.im/circe/circe)

This project provides benchmarks comparing the decoding and encoding performance of [circe][circe] with other open source JSON libraries.

## Running benchmarks

See [Benchmark.scala](src/main/scala/io/circe/benchmarks/Benchmark.scala) for the benchmark definitions.

The following commands will run the individual benchmarks:
```bash
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.ReadingBenchmark"
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.WritingBenchmark"
```

## Results

Here are reading and writing results for circe 0.9.0 on Scala 2.12 against Argonaut, JSON4S, Play JSON, and Spray.
The `Foos` benchmarks measure encoding and decoding a relatively complex map of case classes with several
members, while the `Ints` benchmarks work with a list of integers.

```
Benchmark                            Mode  Cnt      Score     Error  Units
ReadingBenchmark.readFoosArgonaut   thrpt   40   1408.407 ±  27.742  ops/s
ReadingBenchmark.readFoosCirce      thrpt   40   3523.559 ±  32.737  ops/s
ReadingBenchmark.readFoosJson4s     thrpt   40    911.984 ±  12.718  ops/s
ReadingBenchmark.readFoosPlay       thrpt   40   1239.258 ±   9.349  ops/s
ReadingBenchmark.readFoosSpray      thrpt   40   2198.621 ±  19.439  ops/s

ReadingBenchmark.readIntsArgonaut   thrpt   40   8228.883 ±  29.732  ops/s
ReadingBenchmark.readIntsCirce      thrpt   40  18131.610 ± 109.304  ops/s
ReadingBenchmark.readIntsJson4s     thrpt   40   5371.564 ±  60.167  ops/s
ReadingBenchmark.readIntsPlay       thrpt   40   9462.100 ± 257.812  ops/s
ReadingBenchmark.readIntsSpray      thrpt   40  15718.698 ± 236.063  ops/s

Benchmark                            Mode  Cnt      Score     Error  Units
WritingBenchmark.writeFoosArgonaut  thrpt   40   2448.122 ±  40.688  ops/s
WritingBenchmark.writeFoosCirce     thrpt   40   3807.751 ±  50.072  ops/s
WritingBenchmark.writeFoosJson4s    thrpt   40    842.487 ±  24.154  ops/s
WritingBenchmark.writeFoosPlay      thrpt   40   2612.410 ±  14.109  ops/s
WritingBenchmark.writeFoosSpray     thrpt   40   3001.401 ±  40.398  ops/s

WritingBenchmark.writeIntsArgonaut  thrpt   40  15023.165 ± 408.761  ops/s
WritingBenchmark.writeIntsCirce     thrpt   40  29487.908 ± 260.237  ops/s
WritingBenchmark.writeIntsJson4s    thrpt   40   4443.636 ±  64.243  ops/s
WritingBenchmark.writeIntsPlay      thrpt   40   4279.746 ±  19.702  ops/s
WritingBenchmark.writeIntsSpray     thrpt   40  16974.388 ± 219.184  ops/s
```

(Note that "reading" here includes both parsing and decoding, while "writing" is encoding and printing. It's also
possible to run e.g. decoding or encoding benchmarks individually; see the benchmark source for details.)

## Contributors and participation

All circe projects support the [Typelevel][typelevel] [code of conduct][code-of-conduct] and we want
all of their channels (Gitter, GitHub, etc.) to be welcoming environments for everyone.

Please see the [circe contributors' guide][contributing] for details on how to submit a pull
request.

## License

circe-benchmarks is licensed under the **[Apache License, Version 2.0][apache]**
(the "License"); you may not use this software except in compliance with the
License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[apache]: http://www.apache.org/licenses/LICENSE-2.0
[circe]: https://github.com/circe/circe
[code-of-conduct]: http://typelevel.org/conduct.html
[contributing]: https://circe.github.io/circe/contributing.html
[typelevel]: http://typelevel.org/
