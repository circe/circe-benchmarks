# circe-benchmarks

[![Build status](https://img.shields.io/travis/circe/circe-benchmark/master.svg)](https://travis-ci.org/circe/circe-benchmark)
[![Coverage status](https://img.shields.io/codecov/c/github/circe/circe-spray/master.svg)](https://codecov.io/github/circe/circe-benchmark)
[![Gitter](https://img.shields.io/badge/gitter-join%20chat-green.svg)](https://gitter.im/circe/circe)

This project provides benchmarks comparing the decoding and encoding performance of [circe][circe] with other open source libraries.

## Running benchmarks

See [Benchmark.scala](modules/benchmarks/src/main/scala/io/circe/benchmark/Benchmark.scala) for the benchmark definitions.

The following commands will run the individual benchmarks:
```bash
sbt "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmark.EncodingBenchmark"
sbt "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmark.DecodingBenchmark"
sbt "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmark.ParsingBenchmark"
sbt "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmark.PrintingBenchmark"
sbt "benchmarks/jmh:run -i 10 -wi 10 -f 2 -t 1 io.circe.benchmark.CirceDerivationBenchmark"
```

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
