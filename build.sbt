sbtPlugin := true

name := "sbt-dependencies"

organization := "com.proinnovate"

version := "0.1-SNAPSHOT"

// This duplicates the dependencies in project/build.sbt
// This appears to be needed to enable compilation but he project/build.sbt is required to ensure that the
// dependencies are available at runtime.
libraryDependencies ++= Seq(
  "com.weiglewilczek.slf4s" % ("slf4s_" + "2.9.1") % "1.0.7",
  "ch.qos.logback" % "logback-classic" % "1.0.7",
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1",
  "com.assembla.scala-incubator" % "graph-core_2.9.2" % "1.5.1",
  "net.databinder.dispatch" %% "core" % "0.9.1",
  "org.twitter4j" % "twitter4j-core" % "2.2.6",
  "org.apache.httpcomponents" % "httpclient" % "4.2.1",
  "me.lessis" %% "semverfi" % "0.2.0-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "1.8" % "test"
)

// Sonatype publishing settings:

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) 
    Some("snapshots" at nexus + "content/repositories/snapshots") 
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/sroebuck/sbt-dependencies</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:sroebuck/sbt-dependencies.git</url>
    <connection>scm:git:git@github.com:sroebuck/sbt-dependencies.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sroebuck</id>
      <name>Stuart Roebuck</name>
      <url>https://github.com/sroebuck</url>
    </developer>
  </developers>)
