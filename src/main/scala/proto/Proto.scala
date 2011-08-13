package proto

class Proto(
  brain: Brain,
  world: World, 
  _position: Tuple2[Int, Int] = (0,0)
) 
{
  private var currentXPosition = _position._1
  private var currentYPosition = _position._2

  val oxigenUse = 0 
  
  def position() = (currentXPosition, currentYPosition)

  def live {
    world.extractOxigen(oxigenUse)
    brain.process() match {
      case Stop() =>
      case MoveNorth(speed) => currentYPosition += speed
      case MoveSouth(speed) => currentYPosition -= speed
      case MoveEast(speed) => currentXPosition += speed
      case MoveWest(speed) => currentXPosition -= speed
      case _ => 
    }
  }
}
