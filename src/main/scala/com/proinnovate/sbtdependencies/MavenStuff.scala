package com.proinnovate.sbtdependencies

import java.net.URL
import scalax.io.JavaConverters._
import java.io.FileNotFoundException

object MavenStuff {

  def main(args: Array[String]) {
    val (group, artifact, version) = libraries(0)
    for (r ← repositories) {
      println("Repository: " + r.toString)
      println(fullArtifactNames(r, group, artifact, version))
    }
  }

  def Artifacts(repositories: Seq[URL], group: String, artifact: String, version: String): Seq[Artifact] = {
    fullArtifactNames(repositories, group, artifact, version).map {
      case `artifactFileNameRE`(name, _, scalaVersion, _) =>
        Artifact(group = group, name = name, version = version, scalaVersion = scalaVersion)
      case x =>
        Artifact(group = group, name = x, version = version, scalaVersion = "")
    }
  }

  def fullArtifactNames(repositories: Seq[URL], group: String, artifact: String, version: String): Seq[String] =
    repositories.par.flatMap(fullArtifactNames(_, group, artifact, version)).seq

  def fullArtifactNames(repository: URL, group: String, artifact: String, version: String): Seq[String] = {
    val paths = pathsForLibrary(group, artifact, version)
    paths.flatMap {
      path =>
        val url = new URL(repository, path)
        val links = linksForUrl(url).toList
        val dirs = dirsFromUrls(links)
        val artifacts = dirs.collect {
          case x if x.startsWith(artifact) => x
        }
        artifacts
    }
  }

  def linksForUrl(url: URL): Iterator[URL] = {
    try {
      val html = url.asInput.string
      val lm = """href="([^"]+)"""".r
      lm.findAllIn(html).map{case `lm`(url) => url }.map {
        case x => new URL(url, x)
      }
    } catch {
      case e: FileNotFoundException => Iterator()
    }
  }

  private final val lastDirRE = """(/[^/]+)*/([^/]+)/""".r

  private def dirsFromUrls(urls: Seq[URL]): Seq[String] = {
    val paths = urls.map(_.getPath)
    paths.collect {
      case `lastDirRE`(_, dir) => dir
    }
  }

  private def pathsForLibrary(group: String, artifact: String, version: String): Seq[String] = {
    Seq(group.split('.').mkString("/"))
  }

  private lazy val repositories = {
    Seq(
      "http://repo.typesafe.com/typesafe/snapshots/",
      "http://www.scala-tools.org/repo-snapshots/",
      "http://oss.sonatype.org/content/repositories/snapshots/"
    ).map(new URL(_))
  }

  private lazy val libraries = {
    Seq(
      ("com.github.scala-incubator.io", "scala-io-core", "0.4.1"),
      ("com.github.scala-incubator.io", "scala-io-file", "0.4.1"),
      ("com.assembla.scala-incubator", "graph-core_2.9.2", "1.5.1"),
      ("net.databinder.dispatch", "core", "0.9.0"),
      ("org.twitter4j", "twitter4j-core", "2.2.6"),
      ("org.apache.httpcomponents", "httpclient", "4.2.1")
    )
  }

  private final val artifactFileNameRE = """([a-zA-Z\-]+)(_([0-9\.]+(\-[A-Z]*[0-9]*)?))?""".r

  case class Artifact(group: String, name: String, version: String, scalaVersion: String) {

    override def toString = {
      """"%s" %% "%s" %% "%s"""".format(group, name, version) + (if (scalaVersion != "") " (scalaVersion=" + scalaVersion + ")" else "")
    }

  }


}
