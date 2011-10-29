package proto.domain.entity

import proto.domain.World

class AutotrophicProto(world: World, co2Cost: Int) extends Proto {
  private var currentEnergy = 0

  def live() {
    val co2Extracted = world extractCO2 co2Cost
    world increaseOxigenBy co2Extracted
    currentEnergy += co2Extracted / 6
  }

  def energy = currentEnergy

}
