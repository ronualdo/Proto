package proto.domain.entity

abstract class Proto(baseMetabolismCost: Int) {
  protected var currentEnergy = 0
  
  def energy = currentEnergy

  def metabolize() {
    currentEnergy += breathe
    currentEnergy -= metabolismCost
  }

  def metabolismCost = baseMetabolismCost

  def breathe(): Int

  def isAlive = currentEnergy > 0

}
