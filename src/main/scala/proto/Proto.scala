package proto

class Proto() {
  private var energyValue: Int = 0
  val oxigenUse = 10

  def energy = energyValue

  def live() {
    energyValue-=1
  }
}
