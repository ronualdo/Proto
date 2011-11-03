package proto.domain.entity

import proto.domain.World

class AutotrophicProto(world: World, baseMetabolismCost: Int, co2Cost: Int) 
    extends Proto(baseMetabolismCost) {

  def breathe() = {
    val co2Extracted = world extractCO2 co2Cost
    world increaseOxigenBy co2Extracted
    co2Extracted / 6
  }

}
