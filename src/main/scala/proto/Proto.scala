package proto

abstract class Proto(
  brain: Brain,
  world: World, 
  _position: PlaneCoordinates
)
{

  private var currentPosition = _position

  val oxigenUse = 0

  def live {
    breathe()
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

  def breathe(): Unit

  def energy(): Int

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
