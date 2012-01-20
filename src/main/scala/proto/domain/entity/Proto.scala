package proto.domain.entity

abstract class Proto(
  _health: Health,
  protected var _energy: Int, 
  baseMetabolismCost: Int
) {

  def energy = _energy

  def metabolize() {
    if (isAlive) {
      _health.heal
      _energy += breath
      _energy -= metabolismCost
      println(_energy)
      if(_energy < 0) {
        _health.inflictDamage(-_energy)
        _energy = 0
      }
    }
  }

  def metabolismCost = baseMetabolismCost

  def breath(): Int

  def health = _health.value

  def isAlive = _health.value > 0

}
