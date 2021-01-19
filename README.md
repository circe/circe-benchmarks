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

Here are reading and writing results for circe 0.12.0-M4 on Scala 2.13.0 against Argonaut, JSON4S, Play JSON,
and Spray. The `Foos` benchmarks measure encoding and decoding a relatively complex map of case classes with several
members, while the `Ints` benchmarks work with a list of integers.

```
[info] Benchmark                           Mode  Cnt      Score      Error  Units

[info] ReadingBenchmark.readFoosArgonaut  thrpt   20    896.936 ±   16.620  ops/s
[info] ReadingBenchmark.readFoosCirce     thrpt   20   3297.492 ±   71.973  ops/s
[info] ReadingBenchmark.readFoosJson4s    thrpt   20   1153.128 ±   32.758  ops/s
[info] ReadingBenchmark.readFoosJsoniter  thrpt   20  11847.213 ±  423.737  ops/s
[info] ReadingBenchmark.readFoosPlay      thrpt   20   1471.093 ±   40.434  ops/s
[info] ReadingBenchmark.readFoosSpray     thrpt   20   2451.934 ±   57.285  ops/s

[info] ReadingBenchmark.readIntsArgonaut  thrpt   20   7533.894 ±  370.602  ops/s
[info] ReadingBenchmark.readIntsCirce     thrpt   20  16767.628 ±   68.654  ops/s
[info] ReadingBenchmark.readIntsJson4s    thrpt   20   7671.447 ±  182.798  ops/s
[info] ReadingBenchmark.readIntsJsoniter  thrpt   20  60861.281 ± 9193.342  ops/s
[info] ReadingBenchmark.readIntsPlay      thrpt   20   8048.223 ±   99.219  ops/s
[info] ReadingBenchmark.readIntsSpray     thrpt   20  17832.592 ±  277.434  ops/s

---

Benchmark                            Mode  Cnt      Score     Error  Units
ReadingBenchmark.readFoosArgonaut   thrpt   50   1368.041 ±  12.487  ops/s
ReadingBenchmark.readFoosCirce      thrpt   50   4067.121 ±  10.111  ops/s
ReadingBenchmark.readFoosJson4s     thrpt   50   1213.355 ±  20.355  ops/s
ReadingBenchmark.readFoosPlay       thrpt   50   1143.173 ±   7.156  ops/s
ReadingBenchmark.readFoosSpray      thrpt   50   3001.075 ±  12.522  ops/s

ReadingBenchmark.readIntsArgonaut   thrpt   50   8742.412 ±  53.824  ops/s
ReadingBenchmark.readIntsCirce      thrpt   50  18082.714 ±  38.067  ops/s
ReadingBenchmark.readIntsJson4s     thrpt   50   6382.611 ± 162.444  ops/s
ReadingBenchmark.readIntsPlay       thrpt   50   4646.744 ±  26.968  ops/s
ReadingBenchmark.readIntsSpray      thrpt   50  18080.313 ± 194.604  ops/s

Benchmark                            Mode  Cnt      Score     Error  Units
WritingBenchmark.writeFoosArgonaut  thrpt   50   2334.392 ±  47.225  ops/s
WritingBenchmark.writeFoosCirce     thrpt   50   4263.396 ±  92.005  ops/s
WritingBenchmark.writeFoosJson4s    thrpt   50   1633.143 ±   5.642  ops/s
WritingBenchmark.writeFoosPlay      thrpt   50   2177.584 ±   3.430  ops/s
WritingBenchmark.writeFoosSpray     thrpt   50   3588.773 ±  11.324  ops/s

WritingBenchmark.writeIntsArgonaut  thrpt   50  17805.687 ± 463.906  ops/s
WritingBenchmark.writeIntsCirce     thrpt   50  37433.364 ± 146.814  ops/s
WritingBenchmark.writeIntsJson4s    thrpt   50   8180.624 ±  32.511  ops/s
WritingBenchmark.writeIntsPlay      thrpt   50   4011.664 ± 118.561  ops/s
WritingBenchmark.writeIntsSpray     thrpt   50  18803.171 ±  66.265  ops/s
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
