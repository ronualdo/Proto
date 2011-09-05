package proto

class Proto(
  brain: Brain,
  world: World, 
  _position: PlaneCoordinates
) 
{

  val oxigenUse = 0 

  private var currentPosition = _position

  def live {
    world.extractOxigen(oxigenUse)
    brain.process() match {
      case Stop() =>
      case MoveNorth(speed) => moveNorth(speed)
      case MoveSouth(speed) => moveSouth(speed)
      case MoveEast(speed) => moveEast(speed)
      case MoveWest(speed) => moveWest(speed)
      case _ => 
    }
  }

  def position() = currentPosition

  private def moveNorth(speed: Int) {
    currentPosition = currentPosition.decrementY(speed)
  }

  private def moveSouth(speed: Int) {
    currentPosition = currentPosition.incrementY(speed)
  }

  private def moveEast(speed: Int) {
    currentPosition = currentPosition.incrementX(speed)
  }

  private def moveWest(speed: Int) {
    currentPosition = currentPosition.decrementX(speed)
  }

}
