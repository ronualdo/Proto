package proto

import scala.actors.Actor

class Game(val world: World) extends Actor {

  def act() {
    loop {
      world.executeNewCycle
      Thread.sleep(100)
    }
  }

}
