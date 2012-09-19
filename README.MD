sbt-dependencies
================

> *This is pre-release*

`sbt-dependencies` is an sbt plugin for checking the versions of sbt project dependencies for sbt 0.12+.

Once installed it addes the `dependencies-check` command to sbt which checks all the declared dependencies and displays any apparently newer versions along with their Scala library versions.  This is intended to save time for library maintainers who normally have to check library versions by hand to see if any of the dependent libraries have new versions / bug fixes.

The library currently works by crawling the repositories and making reasonable guesses of the version numbers by parsing the filenames.

Here is the current output of a random project I tested it on:

    > dependency-check
    Dependencies:
    org.scala-lang:scala-library:2.9.2
      "org.scala-lang" % "scala-library" % "2.9.3-SNAPSHOT"
      "org.scala-lang" % "scala-library" % "2.10.0-M1"
      "org.scala-lang" % "scala-library" % "2.10.0-M2"
      "org.scala-lang" % "scala-library" % "2.10.0-M3"
      "org.scala-lang" % "scala-library" % "2.10.0-M4"
      "org.scala-lang" % "scala-library" % "2.10.0-M5"
      "org.scala-lang" % "scala-library" % "2.10.0-M6"
      "org.scala-lang" % "scala-library" % "2.10.0-M7"
      "org.scala-lang" % "scala-library" % "2.10.0-SNAPSHOT"
      "org.scala-lang" % "scala-library" % "2.11.0-SNAPSHOT"  
    mysql:mysql-connector-java:5.1.17
      "mysql" % "mysql-connector-java" % "5.1.18"
      "mysql" % "mysql-connector-java" % "5.1.19"
      "mysql" % "mysql-connector-java" % "5.1.20"
      "mysql" % "mysql-connector-java" % "5.1.21"  
    commons-net:commons-net:3.1
      "commons-net" % "commons-net" % "20030211.160026.0"
      "commons-net" % "commons-net" % "20030623.125255.0"
      "commons-net" % "commons-net" % "20030805.205232.0"  
    commons-io:commons-io:2.3
      "commons-io" % "commons-io" % "2.4.0"
      "commons-io" % "commons-io" % "20030203.550.0"  
    com.github.scala-incubator.io:scala-io-file:0.4.1
      "com.github.scala-incubator.io" % "scala-io-file" % "0.5.0-SNAPSHOT" (scalaVersion = 2.9.2)
      "com.github.scala-incubator.io" % "scala-io-file" % "0.5.0-SNAPSHOT" (scalaVersion = 2.10.0-SNAPSHOT)  
    org.apache.pdfbox:pdfbox:1.6.0
      "org.apache.pdfbox" % "pdfbox" % "1.7.0"
      "org.apache.pdfbox" % "pdfbox" % "1.7.1"  
    com.google.guava:guava:12.0
      "com.google.guava" % "guava" % "12.0.1"
      "com.google.guava" % "guava" % "13.0.0"
      "com.google.guava" % "guava" % "13.0.1"  
    org.scalatest:scalatest:2.0.M4:test
      "org.scalatest" % "scalatest" % "0.8.0"
      "org.scalatest" % "scalatest" % "0.9.1"
      "org.scalatest" % "scalatest" % "0.9.3"
      "org.scalatest" % "scalatest" % "0.9.4"
      "org.scalatest" % "scalatest" % "0.9.5"
      "org.scalatest" % "scalatest" % "0.81.0"
      "org.scalatest" % "scalatest" % "1.0.0-RC1"
      "org.scalatest" % "scalatest" % "1.0.0"
      "org.scalatest" % "scalatest" % "1.0.1"
      "org.scalatest" % "scalatest" % "1.1.0-SNAPSHOT"
      "org.scalatest" % "scalatest" % "1.1.0"
      "org.scalatest" % "scalatest" % "1.1.1-SNAPSHOT"
      "org.scalatest" % "scalatest" % "1.2.0"
      "org.scalatest" % "scalatest" % "1.2.1-SNAPSHOT"
      "org.scalatest" % "scalatest" % "1.3.0"
      "org.scalatest" % "scalatest" % "1.4.0-SNAPSHOT"
      "org.scalatest" % "scalatest" % "1.4.1" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.5.0-SNAPSHOT"
      "org.scalatest" % "scalatest" % "1.5.0" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.5.1" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.5.1" (scalaVersion = 2.8.2)
      "org.scalatest" % "scalatest" % "1.6.0-SNAPSHOT" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.6.1" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.6.1" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.6.1" (scalaVersion = 2.9.1-1)
      "org.scalatest" % "scalatest" % "1.6.1" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.6.1" (scalaVersion = 2.9.2)
      "org.scalatest" % "scalatest" % "1.6.2-SNAPSHOT" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.6.3-SNAPSHOT" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.6.3-SNAPSHOT" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.7.0" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.7.0" (scalaVersion = 2.8.2)
      "org.scalatest" % "scalatest" % "1.7.0" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.7.0" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.7.0" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.8.2)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.9.1-1)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.7.1" (scalaVersion = 2.9.2)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.8.2)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.9.1-1)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.7.2" (scalaVersion = 2.9.2)
      "org.scalatest" % "scalatest" % "1.8.0-SNAPSHOT" (scalaVersion = 2.10.0-M1)
      "org.scalatest" % "scalatest" % "1.8.0-SNAPSHOT" (scalaVersion = 2.10.0-M2)
      "org.scalatest" % "scalatest" % "1.8.0-SNAPSHOT" (scalaVersion = 2.10.0-M3)
      "org.scalatest" % "scalatest" % "1.8.0-SNAPSHOT" (scalaVersion = 2.10.0-M4)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.8.1)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.8.2)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.9.1-1)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.8.0" (scalaVersion = 2.9.2)
      "org.scalatest" % "scalatest" % "1.8.1-SNAPSHOT" (scalaVersion = 2.9.0-1)
      "org.scalatest" % "scalatest" % "1.8.1-SNAPSHOT" (scalaVersion = 2.9.0)
      "org.scalatest" % "scalatest" % "1.8.1-SNAPSHOT" (scalaVersion = 2.9.1-1)
      "org.scalatest" % "scalatest" % "1.8.1-SNAPSHOT" (scalaVersion = 2.9.1)
      "org.scalatest" % "scalatest" % "1.8.1-SNAPSHOT" (scalaVersion = 2.9.2)
      "org.scalatest" % "scalatest" % "2.0.0-SNAPSHOT" (scalaVersion = 2.9.0)  
    com.typesafe.akka:akka-actor:2.0.3
      "com.typesafe.akka" % "akka-actor" % "2.1.0-M1"
      "com.typesafe.akka" % "akka-actor" % "2.1.0-M2" (scalaVersion = 2.10.0-M7)
      "com.typesafe.akka" % "akka-actor" % "2.1.0-SNAPSHOT"  
    com.typesafe.akka:akka-slf4j:2.0.3
      "com.typesafe.akka" % "akka-slf4j" % "2.1.0-M1"
      "com.typesafe.akka" % "akka-slf4j" % "2.1.0-SNAPSHOT"  
    com.amazonaws:aws-java-sdk:1.3.12
      "com.amazonaws" % "aws-java-sdk" % "1.3.13"
      "com.amazonaws" % "aws-java-sdk" % "1.3.14"
      "com.amazonaws" % "aws-java-sdk" % "1.3.16"
      "com.amazonaws" % "aws-java-sdk" % "1.3.17"
      "com.amazonaws" % "aws-java-sdk" % "1.3.18"
      "com.amazonaws" % "aws-java-sdk" % "1.3.19"
      "com.amazonaws" % "aws-java-sdk" % "1.3.20"
      "com.amazonaws" % "aws-java-sdk" % "1.3.21"  
    org.apache.httpcomponents:httpclient:4.1.3
      "org.apache.httpcomponents" % "httpclient" % "4.2.0"
      "org.apache.httpcomponents" % "httpclient" % "4.2.1"

Current Status
--------------

This plugin works but is currently dependent on a patched version of the `semverfi` library which isn't currently available from a maven repository so the project will not build without also building the patched version and calling it version `0.2.0-SNAPSHOT`.

Currently this plugin is not being published.  As soon as the dependency issue is resolved it will be published and installation instructions will be added here.

Future plans
------------

It would be great if this library could:

* provide a fuzzy search for library names to bypass the need to look up various Maven repository indexes.
* offer the correct syntax for including the library versions including syntax to cover multiple Scala versions very appropriate versions are available.

