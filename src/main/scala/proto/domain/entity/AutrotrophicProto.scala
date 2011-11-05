package proto.domain.entity

import proto.domain.World

class AutotrophicProto(
  world: World,
  initialHealth: Int,
  maxHealth: Int,
  recoverySpeed: Int,
  initialEnergy: Int, 
  baseMetabolismCost: Int, 
  co2Cost: Int
) 
extends Proto(initialHealth, maxHealth, recoverySpeed, initialEnergy, baseMetabolismCost) {

  def breath() = {
    val co2Extracted = world extractCO2 co2Cost
    world increaseOxigenBy co2Extracted
    co2Extracted / 6
  }

}
