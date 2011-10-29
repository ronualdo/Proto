package proto

class Coordinate(val value: Int, maxValue: Int) {
  
  def +(number: Int) = {
    if(value+number > maxValue) {
      new Coordinate(maxValue, maxValue)
    } else {
      new Coordinate(value+number, maxValue)
    }
  }

  def -(number: Int) = {
    if(value < number) {
      new Coordinate(0, maxValue)
    } else {
      new Coordinate(value-number, maxValue)
    }
  }

}
