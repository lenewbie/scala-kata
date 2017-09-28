name := "scala-kata"

version := "0.0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test

addCommandAlias("updates", ";reload;dependencyUpdates")

wartremoverWarnings ++= Warts.all
