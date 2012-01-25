package proto.ui

import proto.ui.swing.FullScreenApplication
import proto.Config.gameFrame
import proto.Config.game

object ProtoApplication extends FullScreenApplication {

  game.start()

  def top = gameFrame

}
