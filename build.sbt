enablePlugins(JmhPlugin)

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

lazy val catsVersion = "0.8.1"
lazy val jawnVersion = "0.10.4"
lazy val shapelessVersion = "2.3.2"
lazy val refinedVersion = "0.6.0"

lazy val scalaTestVersion = "3.0.0"
lazy val scalaCheckVersion = "1.13.4"
lazy val disciplineVersion = "0.7.2"

lazy val circeVersion = "0.6.1"
lazy val previousCirceVersion = Some("0.6.1")

lazy val baseSettings = Seq(
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
  coverageScalacPluginVersion := "1.3.0",
  (scalastyleSources in Compile) ++= (unmanagedSourceDirectories in Compile).value,
  ivyConfigurations += config("compile-time").hide,
  unmanagedClasspath in Compile ++= update.value.select(configurationFilter("compile-time")),
  unmanagedClasspath in Test ++= update.value.select(configurationFilter("compile-time"))
)

lazy val allSettings = baseSettings

def circeProject(path: String)(project: Project) = {
  val docName = path.split("-").mkString(" ")
  project.settings(
    description := s"circe $docName",
    moduleName := s"circe-$path",
    name := s"Circe $docName",
    allSettings
  )
}

def circeModule(path: String, mima: Option[String]): Project = {
  val id = path.split("-").reduce(_ + _.capitalize)
  Project(id, file(s"modules/$path"))
    .configure(circeProject(path))
    .settings(mimaPreviousArtifacts := mima.map("io.circe" %% moduleName.value % _).toSet)
}

lazy val benchmark = circeModule("benchmark", mima = None)
  .settings(noPublishSettings)
  .settings(
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.3.10",
      "io.argonaut" %% "argonaut" % "6.1",
      "io.spray" %% "spray-json" % "1.3.2",
      "io.github.netvl.picopickle" %% "picopickle-core" % "0.2.1",
      "io.github.netvl.picopickle" %% "picopickle-backend-jawn" % "0.2.1",
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
      compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
    ),
    libraryDependencies ++= circeDependencies
  )
  .enablePlugins(JmhPlugin)


lazy val circeDependencies = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-jawn"
).map(_ % circeVersion)

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)
