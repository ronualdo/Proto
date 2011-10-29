package proto

import scala.actors.Actor
import scala.actors.Actor._

import proto.domain.World

class Game (
  private val world: World, 
  private val renderer: Renderer
) 
{
  private var gameCycleActor: Actor = _

  def start() {
    gameCycleActor = actor {
      world.executeNewCycle
      world.renderUsing (renderer)
      renderer.show
      wait(100)
      reactWithin(100) {
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
