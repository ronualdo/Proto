package proto

import domain.entity.{Health, AutotrophicProto}
import domain.World
import domain.Game
import ui.GameFrame

object Config {
  implicit val world = new World(1000, 1000, (800, 600))
  implicit val gameFrame = new GameFrame
  implicit val game = new Game

  val proto = new AutotrophicProto(
    world,
    new Health(1000, 1, 1),
    initialEnergy = 10,
    baseMetabolismCost = 1,
    co2Cost = 1
  )

  world.add(proto)
}