package com.proinnovate.sbtdependencies

import sbt._
import Keys._
import semverfi.Version

object SbtDependencies extends Plugin {

  // Note to self: base this plugin on the example here: <https://github.com/harrah/xsbt/wiki/Advanced-Command-Example>

  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand: Command = 
    Command.command("dependency-check") { (state: State) =>
      println("Dependencies:")

      val extracted: Extracted = Project.extract(state)

      val modulesOpt: Option[Seq[ModuleID]] = libraryDependencies in extracted.currentRef get extracted.structure.data

      for {
        modules ← modulesOpt
        module ← modules
      } {
        println(module)
        val group = module.organization
        val artifact = module.name
        val version = module.revision
        val artifacts = MavenStuff.artifacts(repositories, group, artifact, versionOpt = Some(Version(version)))
        println(artifacts.mkString("  ", "\n  ", "  "))
      }
      state
    }

    private lazy val repositories = {
      // XXX: Note that URLs must end with a slash at the present time!
      Seq(
        "http://repo.typesafe.com/typesafe/releases/",
        "http://repo.typesafe.com/typesafe/snapshots/",
        "http://www.scala-tools.org/repo-snapshots/",
        "http://oss.sonatype.org/content/repositories/releases/",
        "http://oss.sonatype.org/content/repositories/snapshots/"
      ).map(new URL(_))
    }

}

