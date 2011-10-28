package proto

class AutotrophicProto (
  brain: Brain, 
  world: World, 
  initialPosition: PlaneCoordinates,
  co2Use: Int
) extends Proto(brain, world, initialPosition) {

  private var currentEnergy = 10
  
  override def energy() = currentEnergy

  override def breathe() {
    val co2Extracted = world.extractCO2(co2Use)
    val (energyProduced, o2Produced) = processCO2(co2Extracted)
    currentEnergy += energyProduced
    world.increaseOxigenBy(o2Produced)
  }

  private def processCO2(co2Consumed: Int): Tuple2[Int, Int] = {
    val energyProduced = co2Consumed / 6
    val oxigenProduced = energyProduced
    (energyProduced, oxigenProduced)
  }
}
