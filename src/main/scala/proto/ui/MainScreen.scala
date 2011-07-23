package proto.ui

import java.awt.{GraphicsEnvironment, Dimension}
import scala.swing.MainFrame
import scala.swing.RichWindow.Undecorated
import proto.ui.swing.FullScreenApplication

object MainScreen extends FullScreenApplication {

  def top = new MainFrame with Undecorated {
    
  }

}
