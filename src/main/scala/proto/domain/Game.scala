package proto.domain

import scala.actors.Actor
import scala.actors.Actor._

import proto.ui.Renderer

class Game (
  private implicit val world: World,
  private implicit val renderer: Renderer
) 
{
  private var gameCycleActor: Actor = _

  def start() {
    gameCycleActor = actor {
      world.executeNewCycle

      renderer.clearScreen()
      world.renderUsing (renderer)
      renderer.show

      wait(100)

      reactWithin(100) {
        case 'execute => start
        case 'stop => {}
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
