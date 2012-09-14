package com.proinnovate.sbtdependencies

import java.net.URL
import scalax.io.JavaConverters._
import java.io.FileNotFoundException
import com.weiglewilczek.slf4s.Logging
import semverfi.{SemVerOrdering, SemVersion, Version}

object MavenStuff extends Logging {

  def artifacts(repositories: Seq[URL], group: String, artifact: String, versionOpt: Option[SemVersion] = None): Seq[Artifact] = {
    fullArtifactNames(repositories, group, artifact).filter {
      a =>
        val r = for {
          v <- a.versionOpt
          rv <- versionOpt
        } yield {
          SemVerOrdering.compare(rv, v) < 0
        }
        r.getOrElse(false)
    }
  }

  def fullArtifactNames(repositories: Seq[URL], group: String, artifact: String): Seq[Artifact] =
    repositories.par.flatMap(fullArtifactNames(_, group, artifact)).seq
//    repositories.flatMap(fullArtifactNames(_, group, artifact))

  def fullArtifactNames(repository: URL, group: String, artifact: String): Set[Artifact] = {
    val paths = suggestedPathsForLibrary(group, artifact)
    logger.debug("paths = " + paths)
    paths.flatMap {
      path =>
        val url = new URL(repository, path)
        logger.debug("url = " + url)
        val links = linksForUrl(url).toList
        logger.debug("links = " + links)
        val dirs = dirsFromUrls(links)
        logger.debug("dirs = " + dirs)
        val dirArtifacts = dirs.collect {
          case dirName => dirName match {
            case `artifactFileNameRE`(name, scalaVersion) if scalaVersion != null =>
              (dirName, Artifact(group = group, name = name, scalaVersionOpt = Some(Version(scalaVersion))))
            case x =>
              (dirName, Artifact(group = group, name = x))
          }
        }.filter(_._2.name == artifact)

        (for ( (dirName, artifact) <- dirArtifacts) yield {
          val newUrl = new URL(url, dirName + "/")
          val links = linksForUrl(newUrl).toList
          val dirs = dirsFromUrls(links)
          logger.debug("dirs = " + dirs)
          val versions = dirs.collect {
            case `versionFileNameRE`(version) => version
          }
          logger.debug("versions = " + versions)
          versions.map {
            version => artifact.copy(versionOpt = Some(Version(version)))
          }
        }).flatten
    }.toSet
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

  private def dirsFromUrls(urls: Seq[URL]): Seq[String] = {
    val paths = urls.map(_.getPath)
    paths.collect {
      case `lastDirRE`(_, dir) => dir
    }
  }

  private def suggestedPathsForLibrary(group: String, artifact: String): Seq[String] = {
    Seq(group.split('.').mkString("", "/", "/"))
  }

  private final val lastDirRE = """(/[^/]+)*/([^/]+)/""".r

  private final val artifactFileNameRE = """([a-zA-Z\-]+)(?:_([0-9\.]+(?:\-[A-Z]*[0-9]*)?))?""".r

  private final val versionFileNameRE = """([0-9\.]+(?:\-[A-Z]*[0-9]*)?)""".r

}
