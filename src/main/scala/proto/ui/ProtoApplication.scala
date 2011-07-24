package proto.ui

import java.awt.{Color, GraphicsEnvironment, Dimension}

import scala.swing.RichWindow.Undecorated

import proto.ui.swing.{FullScreenApplication, DirectRenderingFrame}
import proto.{Game, Proto, World}

object ProtoApplication extends FullScreenApplication {
  val gameFrame = new GameFrame
  val world = new World(1000)
  world.add(new Proto)
  val game = new Game(world, gameFrame)

  def top = gameFrame

  override def startup(args: Array[String]) {
    super.startup(args)
    game.start
  }
  
  override def shutdown() {
    super.shutdown
    game.stop
  }
}
