package proto.ui

import java.awt.{Color, GraphicsEnvironment, Dimension}

import scala.swing.RichWindow.Undecorated

import proto.ui.swing.{FullScreenApplication, DirectRenderingFrame}
import proto.{Game, World}

object MainScreen extends FullScreenApplication {

  def top = new GameFrame

}
