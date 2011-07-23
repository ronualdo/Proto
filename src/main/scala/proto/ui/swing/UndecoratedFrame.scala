package proto.ui.swing

import javax.swing.JFrame
import scala.swing._

class UndecoratedFrame extends Window {
  override lazy val peer = new JFrame with InterfaceMixin

  peer.setUndecorated(true)

  override def closeOperation() {
    super.closeOperation
    println("closing")
    System.exit(0)

  }
}
