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

Here are reading and writing results for circe 0.11.1 on Scala 2.12.8 against Argonaut, JSON4S, Play JSON,
and Spray. The `Foos` benchmarks measure encoding and decoding a relatively complex map of case classes with several
members, while the `Ints` benchmarks work with a list of integers.

```
Benchmark                            Mode  Cnt      Score      Error  Units
ReadingBenchmark.readFoosArgonaut   thrpt   40   1364.945 ±   24.378  ops/s
ReadingBenchmark.readFoosCirce      thrpt   40   3891.951 ±    7.710  ops/s
ReadingBenchmark.readFoosJson4s     thrpt   40   1010.018 ±   19.810  ops/s
ReadingBenchmark.readFoosPlay       thrpt   40   1541.661 ±    2.752  ops/s
ReadingBenchmark.readFoosSpray      thrpt   40   2237.354 ±   86.858  ops/s

ReadingBenchmark.readIntsArgonaut   thrpt   40   8912.254 ±  244.544  ops/s
ReadingBenchmark.readIntsCirce      thrpt   40  17858.676 ±   23.154  ops/s
ReadingBenchmark.readIntsJson4s     thrpt   40   5337.795 ±   67.310  ops/s
ReadingBenchmark.readIntsPlay       thrpt   40   6782.925 ±  109.757  ops/s
ReadingBenchmark.readIntsSpray      thrpt   40  15199.081 ±  385.915  ops/s

Benchmark                            Mode  Cnt      Score      Error  Units
WritingBenchmark.writeFoosArgonaut  thrpt   40   2247.399 ±    7.819  ops/s
WritingBenchmark.writeFoosCirce     thrpt   40   3814.047 ±   11.015  ops/s
WritingBenchmark.writeFoosJson4s    thrpt   40   1053.587 ±   34.558  ops/s
WritingBenchmark.writeFoosPlay      thrpt   40   1978.124 ±    2.657  ops/s
WritingBenchmark.writeFoosSpray     thrpt   40   3259.941 ±    8.285  ops/s

WritingBenchmark.writeIntsArgonaut  thrpt   40  16803.414 ±  427.511  ops/s
WritingBenchmark.writeIntsCirce     thrpt   40  31748.367 ± 1081.470  ops/s
WritingBenchmark.writeIntsJson4s    thrpt   40   5136.692 ±  113.600  ops/s
WritingBenchmark.writeIntsPlay      thrpt   40   3890.009 ±  187.022  ops/s
WritingBenchmark.writeIntsSpray     thrpt   40  17625.977 ±   35.083  ops/s
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
