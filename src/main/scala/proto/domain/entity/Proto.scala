package proto.domain.entity

abstract class Proto(
  initialHealth: Int,
  maxHealth: Int,
  recoverySpeed: Int,
  initialEnergy: Int, 
  baseMetabolismCost: Int
) {
  protected var currentEnergy = initialEnergy

  private var currentHealth = initialHealth

  def energy = currentEnergy

  def metabolize() {
    if (isAlive) {
      if(currentHealth < maxHealth) {
        currentHealth += recoverySpeed
      }

      currentEnergy += breath
      currentEnergy -= metabolismCost

      if(currentEnergy < 0) {
        currentHealth += currentEnergy
      }
    }
  }

  def metabolismCost = baseMetabolismCost

  def breath(): Int

  def health = currentHealth

  def isAlive = currentEnergy > 0

}
