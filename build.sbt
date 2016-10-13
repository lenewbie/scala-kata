name := "scala-kata"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % Test

addCommandAlias("updates", ";reload;dependencyUpdates")