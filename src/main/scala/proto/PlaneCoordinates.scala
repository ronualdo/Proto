package proto

class PlaneCoordinates(x: Int, y: Int, maxXValue: Int, maxYValue: Int) {
  
  val xCoordinate = new Coordinate(x, maxXValue)
  val yCoordinate = new Coordinate(y, maxYValue)

  class Coordinate(val value: Int, maxValue: Int) {
    def +(incrementValue: Int) = {
      val newValue = value + incrementValue

      if(newValue <= maxValue) {
        new Coordinate(value+incrementValue, maxValue)
      } else {
        new Coordinate(maxValue, maxValue)
      }
    }

    def -(decrementValue: Int) = {
      if (decrementValue <= value) {
        new Coordinate(value - decrementValue, maxValue)
      } else {
        new Coordinate(0, maxValue)
      }
    }

    override def equals(anything: Any) = {
      if(anything.isInstanceOf[Coordinate]) {
        val c = anything.asInstanceOf[Coordinate]
        value == c.value
      } else {
        false
      }
    }

    override def toString() = value.toString
  }

  def incrementX(incrementValue: Int) = {
    val newXCoordinate = xCoordinate + incrementValue

    new PlaneCoordinates(newXCoordinate.value, yCoordinate.value, maxXValue, maxYValue) 
  }

  def incrementY(incrementValue: Int) = {
    val newYCoordinate = yCoordinate + incrementValue

    new PlaneCoordinates(xCoordinate.value, newYCoordinate.value, maxXValue, maxYValue)
  }

  def decrementX(decrementValue: Int) = {
    val newXCoordinate = xCoordinate - decrementValue

    new PlaneCoordinates(newXCoordinate.value, yCoordinate.value, maxXValue, maxYValue) 
  }

  def decrementY(decrementValue: Int) = {
    val newYCoordinate = yCoordinate - decrementValue

    new PlaneCoordinates(xCoordinate.value, newYCoordinate.value, maxXValue, maxYValue)
  }

  override def toString() = xCoordinate + "," + yCoordinate 

  override def equals(position: Any) = {
    if(position.isInstanceOf[PlaneCoordinates]) {
      val p = position.asInstanceOf[PlaneCoordinates]
      
      xCoordinate == p.xCoordinate && yCoordinate == p.yCoordinate
    } else {
      false
    }
  }
}
