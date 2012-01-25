name := "Proto"

version := "0.0.1"

scalaVersion := "2.9.1"

fork in run := true

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.9.1" % "1.6.1" % "test",
  "org.jmock" % "jmock-legacy" % "2.5.1" % "test",
  "org.scala-lang" % "scala-swing" % "2.9.1",
  "org.mockito" % "mockito-core" % "1.9.0"
)
