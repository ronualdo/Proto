package proto.ui.swing

import java.awt.GraphicsEnvironment

import scala.swing._

abstract class FullScreenApplication 
    extends SwingApplication {
  
  val screenDevice = localGraphicsEnvironment.getDefaultScreenDevice
  
  def top: Frame

  override def startup(args: Array[String]) {
    val mainFrame = top

    try {
      screenDevice.setFullScreenWindow(mainFrame.peer)
    } catch {
      case e: Exception => shutdown
    }
  }

  override def shutdown() {
    println("shutdown")
    screenDevice.setFullScreenWindow(null)
  }

  private def localGraphicsEnvironment = {
    GraphicsEnvironment.getLocalGraphicsEnvironment
  }
}
