package proto

import proto.ui.swing.FullScreenApplication
import proto.Config.gameFrame
import proto.Config.game

object ProtoApplication extends FullScreenApplication {
  println ("executing")
  game.start()

  def top = gameFrame

}
