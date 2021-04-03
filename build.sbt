organization in ThisBuild := "io.circe"

val compilerOptions = Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture"
)

val circeVersion = "0.13.0"
val scalaTestVersion = "3.2.7"

def priorTo2_13(scalaVersion: String): Boolean =
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, minor)) if minor < 13 => true
    case _                              => false
  }

val baseSettings = Seq(
  scalacOptions ++= compilerOptions,
  scalacOptions in (Compile, console) ~= {
    _.filterNot(Set("-Ywarn-unused-import"))
  },
  scalacOptions in (Test, console) ~= {
    _.filterNot(Set("-Ywarn-unused-import"))
  },
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  ),
  coverageHighlighting := true
)

val circeDependencies = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-jawn"
).map(_ % circeVersion)

lazy val benchmark = project
  .in(file("."))
  .settings(baseSettings ++ noPublishSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "io.argonaut" %% "argonaut" % "6.3.3",
      "io.circe" %% "circe-derivation" % "0.12.0-M7",
      "io.circe" %% "circe-jackson29" % "0.13.0",
      "io.spray" %% "spray-json" % "1.3.6",
      "org.json4s" %% "json4s-jackson" % "3.6.11",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.6.4",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.6.4",
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    ),
    libraryDependencies ++= circeDependencies
  )
  .enablePlugins(JmhPlugin)

lazy val noPublishSettings = Seq(
  publish := {},
  publishLocal := {},
  publishArtifact := false
)
