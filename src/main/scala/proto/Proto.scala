package proto

class Proto(val oxigenCost: Int) {
  private var energyValue: Int = 0

  def energy = energyValue

  def live() {
    energyValue-=1
  }
}
