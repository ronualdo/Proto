package proto

class Proto(
  size: Tuple2[Int, Int], 
  position: Tuple2[Int, Int] = (0,0)
)
{ 
  private var energyValue: Int = 0
  private var currentPosition = position
  val metabolismCost = 10
  val oxigenUse = 10

  def energy = energyValue

  def position(): Tuple2[Int, Int] = currentPosition

  def live() {
    energyValue-=metabolismCost
  }

  def move(position: Tuple2[Int, Int]) {
    currentPosition = position
  }
}
