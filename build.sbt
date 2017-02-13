organization in ThisBuild := "io.circe"

val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture"
)

val circeVersion = "0.7.0"
val scalaTestVersion = "3.0.1"

val baseSettings = Seq(
  scalacOptions ++= compilerOptions ++ (
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, p)) if p >= 11 => Seq("-Ywarn-unused-import")
      case _ => Nil
    }
  ),
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
  coverageHighlighting := (
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 10)) => false
      case _ => true
    }
  ),
  coverageScalacPluginVersion := "1.3.0"
)

val circeDependencies = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-jawn"
).map(_ % circeVersion)

lazy val benchmark = project.in(file("."))
  .settings(baseSettings ++ noPublishSettings)
  .settings(
    libraryDependencies ++= Seq(
      "io.argonaut" %% "argonaut" % "6.2-RC2",
      "io.spray" %% "spray-json" % "1.3.3",
      "org.json4s" %% "json4s-jackson" % "3.5.0",
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
      compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
    ),
    libraryDependencies ++= circeDependencies,
    libraryDependencies ++= (
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 10)) => Nil
        case _ => Seq(
          "com.typesafe.play" %% "play-json" % "2.6.0-M1"
        )
      }
    ),
    libraryDependencies ++= (
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 12)) => Nil
        case _ => Seq(
          "io.github.netvl.picopickle" %% "picopickle-core" % "0.3.1",
          "io.github.netvl.picopickle" %% "picopickle-backend-jawn" % "0.3.1"
        )
      }
    )
  )
  .enablePlugins(JmhPlugin)

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)
