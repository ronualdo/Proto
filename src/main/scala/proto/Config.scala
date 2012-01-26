package proto

import domain.World
import domain.Game
import ui.GameFrame

object Config {
  implicit val world = new World(1000, 1000, (800, 600))
  implicit val gameFrame = new GameFrame
  implicit val game = new Game
}