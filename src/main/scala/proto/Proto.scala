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
      case MoveNorth(speed) => moveNorth(speed)
      case MoveSouth(speed) => moveSouth(speed)
      case MoveEast(speed) => moveEast(speed)
      case MoveWest(speed) => moveWest(speed)
      case _ => 
    }
  }

  private def moveNorth(speed: Int) {
    currentYPosition = decrementPosition(currentYPosition, speed)
  }

  private def moveSouth(speed: Int) {
    currentYPosition = limitedIncrementPosition(currentYPosition, speed, world.height)
  }

  private def moveEast(speed: Int) {
    currentXPosition = limitedIncrementPosition(currentXPosition, speed, world.width)
  }

  private def moveWest(speed: Int) {
    currentXPosition = decrementPosition(currentXPosition, speed)
  }

  private def limitedIncrementPosition(position: Int, speed: Int, limit: Int) = {
    val newPosition = position + speed
    
    if (newPosition > limit) {
      limit
    } else {
      newPosition
    }
  }

  private def decrementPosition(position: Int, speed: Int) = {
    if(position > speed) {
      position - speed
    } else {
      0
    }
  }
}
