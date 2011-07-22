package proto

import scala.actors.Actor
import scala.actors.Actor._

class Game(val world: World) {
  private var gameCycleActor: Actor = _

  def start() {
    gameCycleActor = actor {
      world.executeNewCycle
      wait(100)
      react {
        case 'execute => start
        case 'stop => println("quiting")
      }
    }
  }
  
  private def wait(time: Int) {
    actor {
      Thread.sleep(time)
      gameCycleActor ! 'execute
    }
  }

  def stop() {
    gameCycleActor ! 'stop
  }

}
