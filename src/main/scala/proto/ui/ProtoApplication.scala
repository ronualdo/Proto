package proto.ui

import java.awt.{Color, GraphicsEnvironment, Dimension}

import scala.swing.RichWindow.Undecorated

import proto.ui.swing.{FullScreenApplication, DirectRenderingFrame}
import proto.Game
import proto.domain.World
import proto.domain.entity.Proto

object ProtoApplication extends FullScreenApplication {
  val gameFrame = new GameFrame

  def top = gameFrame

}
