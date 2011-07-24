package proto

import scala.actors.Actor
import scala.actors.Actor._

class Game(val world: World, val renderer: Renderer) {
  private var gameCycleActor: Actor = _

  def start() {
    gameCycleActor = actor {
      world.executeNewCycle
      world.renderUsing (renderer)
      wait(100)
      reactWithin(100) {
        case 'execute => start
        case 'stop => println("quiting")
      }
    }
  }
  
  private def wait(time: Int) {
    println("waiting")
    actor {
      Thread.sleep(time)
      gameCycleActor ! 'execute
    }
  }

  def stop() {
    gameCycleActor ! 'stop
  }

}
