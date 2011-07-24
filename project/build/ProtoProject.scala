import sbt._

class ProtoProject(info: ProjectInfo) extends DefaultProject(info) {
  
  val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
  val jmock = "org.jmock" % "jmock-legacy" % "2.5.1" % "test"
  val scalaSwing = "org.scala-lang" % "scala-swing" % "2.9.0-1"

  override def mainClass = Some("proto.ui.ProtoApplication")
}
