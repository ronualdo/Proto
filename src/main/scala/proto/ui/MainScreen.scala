package proto.ui

import java.awt.{Color, GraphicsEnvironment, Dimension}

import scala.swing.RichWindow.Undecorated
import proto.ui.swing.{FullScreenApplication, DirectRenderingFrame}

object MainScreen extends FullScreenApplication {

  def top = new DirectRenderingFrame with Undecorated {
    background = Color.BLACK
  }

}
