package io.circe.benchmarks

import cats.kernel.Eq

case class Foo(s: String, d: Double, i: Int, l: Long, bs: List[Boolean])

object Foo extends ArgonautFooInstances with CirceFooInstances with SprayFooInstances
    with PlayFooInstances with PicopickleFooInstances {
  implicit val eqFoo: Eq[Foo] = Eq.fromUniversalEquals[Foo]
}
