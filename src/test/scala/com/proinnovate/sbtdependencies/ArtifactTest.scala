package com.proinnovate.sbtdependencies

import com.weiglewilczek.slf4s.Logging
import org.scalatest.FunSuite
import semverfi.Version

class ArtifactTest extends FunSuite with Logging {

  test("Ordering") {
    val x = Artifact("com.github.scala-incubator.io", "scala-io-core", Some(Version("0.4.1")), Some(Version("2.9.1")))
    val x2 = Artifact("com.github.scala-incubator.io", "scala-io-core", Some(Version("0.4.1")), Some(Version("2.9.1")))
    val y = Artifact("com.github.scala-incubator.io", "scala-io-core", Some(Version("0.4.2")), Some(Version("2.9.1")))
    val z = Artifact("com.github.scala-incubator.io", "scala-io-core", Some(Version("0.4.2")), Some(Version("2.10.0")))

    assert(x === x2)
    assert(x < y)
    assert(x < z)
    assert(y < z)
    assert(x != y)
    assert(x != z)
    assert(y != z)
    assert(Seq(x,x2).toSet.size === 1)

  }

}
