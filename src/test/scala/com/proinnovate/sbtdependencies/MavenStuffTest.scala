package com.proinnovate.sbtdependencies

import org.scalatest.FunSuite
import java.net.URL
import com.weiglewilczek.slf4s.Logging
import semverfi.Version

/*
 * Copyright (c) Stuart Roebuck, 2012
 */
class MavenStuffTest extends FunSuite with Logging {

  test("Something") {
    val (group, artifact, version) = testLibraries(0)
    logger.info("\n" + MavenStuff.artifacts(repositories, group, artifact, Some(Version(version))).sorted.mkString("\n"))
  }


  private lazy val testLibraries = {
    Seq(
      ("com.github.scala-incubator.io", "scala-io-core", "0.4.1"),
      ("com.github.scala-incubator.io", "scala-io-file", "0.4.1"),
      ("com.assembla.scala-incubator", "graph-core_2.9.2", "1.5.1"),
      ("net.databinder.dispatch", "core", "0.9.0"),
      ("org.twitter4j", "twitter4j-core", "2.2.6"),
      ("org.apache.httpcomponents", "httpclient", "4.2.1")
    )
  }

  private lazy val repositories = {
    Seq(
      "http://repo.typesafe.com/typesafe/snapshots/",
      "http://www.scala-tools.org/repo-snapshots/",
      "http://oss.sonatype.org/content/repositories/snapshots/"
    ).map(new URL(_))
  }


}
