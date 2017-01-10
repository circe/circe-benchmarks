# circe-benchmarks

[![Build status](https://img.shields.io/travis/circe/circe-benchmarks/master.svg)](https://travis-ci.org/circe/circe-benchmarks)
[![Coverage status](https://img.shields.io/codecov/c/github/circe/circe-benchmarks/master.svg)](https://codecov.io/github/circe/circe-benchmarks)
[![Gitter](https://img.shields.io/badge/gitter-join%20chat-green.svg)](https://gitter.im/circe/circe)

This project provides benchmarks comparing the decoding and encoding performance of [circe][circe] with other open source JSON libraries.

## Running benchmarks

See [Benchmark.scala](src/main/scala/io/circe/benchmarks/Benchmark.scala) for the benchmark definitions.

The following commands will run the individual benchmarks:
```bash
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.EncodingBenchmark"
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.DecodingBenchmark"
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.ParsingBenchmark"
sbt "jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmarks.PrintingBenchmark"
```

## Results

Here are decoding and encoding results for circe 0.7.0-M2 on Scala 2.11 against Argonaut, Play JSON, Picopickle, and
Spray. The `Foos` benchmarks measure encoding and decoding a relatively complex map of case classes with several
members, while the `Ints` benchmarks work with a list of integers.

```
Benchmark                          Mode  Cnt      Score      Error  Units
DecodingBenchmark.decodeFoosC     thrpt  100  10161.304 ±  102.923  ops/s
DecodingBenchmark.decodeFoosA     thrpt  100   2786.826 ±   43.675  ops/s
DecodingBenchmark.decodeFoosP     thrpt  100   1974.245 ±    9.858  ops/s
DecodingBenchmark.decodeFoosPico  thrpt  100   2106.936 ±   31.172  ops/s
DecodingBenchmark.decodeFoosS     thrpt  100   8193.956 ±   38.757  ops/s

DecodingBenchmark.decodeIntsC     thrpt  100  50399.910 ±  156.052  ops/s
DecodingBenchmark.decodeIntsA     thrpt  100  20472.141 ±  412.459  ops/s
DecodingBenchmark.decodeIntsP     thrpt  100  15146.795 ±   68.217  ops/s
DecodingBenchmark.decodeIntsPico  thrpt  100  15760.650 ±  972.880  ops/s
DecodingBenchmark.decodeIntsS     thrpt  100  81535.344 ±  293.525  ops/s

Benchmark                          Mode  Cnt      Score      Error  Units
EncodingBenchmark.encodeFoosC     thrpt  100   8565.560 ±  107.385  ops/s
EncodingBenchmark.encodeFoosA     thrpt  100   6736.379 ±   15.906  ops/s
EncodingBenchmark.encodeFoosP     thrpt  100   1765.981 ±    4.078  ops/s
EncodingBenchmark.encodeFoosPico  thrpt  100   5779.484 ±   26.803  ops/s
EncodingBenchmark.encodeFoosS     thrpt  100   6561.330 ±   29.160  ops/s

EncodingBenchmark.encodeIntsC     thrpt  100  98093.604 ±  223.612  ops/s
EncodingBenchmark.encodeIntsA     thrpt  100  80580.759 ±  323.298  ops/s
EncodingBenchmark.encodeIntsP     thrpt  100  29823.703 ±  451.211  ops/s
EncodingBenchmark.encodeIntsPico  thrpt  100  37110.701 ±  145.859  ops/s
EncodingBenchmark.encodeIntsS     thrpt  100  43733.808 ± 2810.698  ops/s
```

(Please see the commands above for the parsing and printing benchmarks.)

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
