package proto

class Proto(
  size: Tuple2[Int, Int], 
  position: Tuple2[Int, Int] = (0,0)
)
{ 
  private var energyValue: Int = 0
  private var currentPosition = position
  val oxigenUse = 10

  def energy = energyValue

  def position(): Tuple2[Int, Int] = currentPosition

  def live() {
    val (x, y) = currentPosition
    move((x, y+1))
    energyValue-=1
  }

  def move(position: Tuple2[Int, Int]) {
    println("moving"+ position)
    currentPosition = position
  }
}
