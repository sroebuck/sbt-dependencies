// This file should contain any dependencies that need to be loaded with the plugin at runtime.

libraryDependencies ++= Seq(
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1",
  "com.assembla.scala-incubator" % "graph-core_2.9.2" % "1.5.1",
  "net.databinder.dispatch" %% "core" % "0.9.0",
  "org.twitter4j" % "twitter4j-core" % "2.2.6",
  "org.apache.httpcomponents" % "httpclient" % "4.2.1"
)
