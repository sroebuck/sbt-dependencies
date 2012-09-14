package com.proinnovate.sbtdependencies

import semverfi.SemVersion

/*
 * Copyright (c) Stuart Roebuck, 2012
 */

case class Artifact(group: String, name: String, versionOpt: Option[SemVersion] = None,
                    scalaVersionOpt: Option[SemVersion] = None) {

  override def toString = {
    """"%s" %% "%s" %% "%s"""".format(group, name, versionOpt) +
      scalaVersionOpt.map(v => " (scalaVersion = " + v + ")").getOrElse("")
  }

}
