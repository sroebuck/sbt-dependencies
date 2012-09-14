package com.proinnovate.sbtdependencies

import semverfi._

/*
 * Copyright (c) Stuart Roebuck, 2012
 */

case class Artifact(group: String, name: String, versionOpt: Option[SemVersion] = None,
                    scalaVersionOpt: Option[SemVersion] = None) extends Ordered[Artifact] {

  def compare(that: Artifact) = {
    val groupCompare = this.group compare that.group
    if (groupCompare != 0) groupCompare
    else {
      val nameCompare = this.name compare that.name
      if (nameCompare != 0) nameCompare
      else {
        val versionCompare: Int = this.versionOpt.flatMap(thisv => that.versionOpt.map(thatv =>
          SemVerOrdering.compare(thisv, thatv))).getOrElse(0)
        if (versionCompare != 0) versionCompare
        else {
          this.scalaVersionOpt.flatMap(thisv => that.scalaVersionOpt.map(thatv =>
            SemVerOrdering.compare(thisv, thatv))).getOrElse(0)
        }
      }
    }
  }


  override def equals(obj: Any) = {
    obj match {
      case that: Artifact => compare(that) == 0
      case _ => false
    }
  }

  override def toString = {
    """"%s" %% "%s" %% "%s"""".format(group, name, versionOpt.map(v => Show(v)).getOrElse("")) +
      scalaVersionOpt.map(v => " (scalaVersion = " + Show(v) + ")").getOrElse("")
  }

}
