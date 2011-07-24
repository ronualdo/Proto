package proto.ui

import java.awt.{Color, GraphicsEnvironment, Dimension}

import scala.swing.RichWindow.Undecorated

import proto.ui.swing.{FullScreenApplication, DirectRenderingFrame}
import proto.{Game, World}

object ProtoApplication extends FullScreenApplication {
  val gameFrame = new GameFrame
  val game = new Game(new World(1000), gameFrame)
  
  def top = gameFrame
  
  override def shutdown() {
    super.shutdown
    game.stop
  }
}
