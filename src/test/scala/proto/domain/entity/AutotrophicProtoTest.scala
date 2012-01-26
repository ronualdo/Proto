package proto.domain.entity

import org.scalatest.WordSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers

import org.mockito.Mockito._

import proto.domain.World

class AutrotrophicProtoTest extends WordSpec
    with ShouldMatchers
    with MockitoSugar {

  "An autrophic proto" should {
    
    "consum world co2 after metabolize" in {
      
      val world = mock[World]
      val health = mock[Health]
      val co2Cost = 20
      val proto = new AutotrophicProto(
        world,
        health,
        initialEnergy = 10,
        baseMetabolismCost = 10,
        co2Cost = co2Cost
      )

      when(health.value) thenReturn (100)

      proto.metabolize()

      verify(world).extractCO2(co2Cost)

    }
  }

  "generate an O2 ammount relative to the CO2 ammount extracted" in {

    val world = mock[World]
    val health = mock[Health]
    val co2Extracted = 10
    val proto = new AutotrophicProto(
      world,
      health,
      initialEnergy=10,
      baseMetabolismCost=10,
      co2Cost=20
    )

    when(health.value) thenReturn 100
    when(world.extractCO2(20)) thenReturn(co2Extracted);

    proto.metabolize()

    verify(world).increaseOxigenBy (co2Extracted)
  }

  "generate energy in a proportial of 1/6 of the CO2 processed" in {

    val world = mock[World]
    val health = mock[Health]
    val co2Extracted = 20
    val proto = new AutotrophicProto(
      world,
      health,
      initialEnergy = 10,
      baseMetabolismCost = 0,
      co2Cost = 30
    )
    val initialEnergy: Int = proto.energy
    val expectedEnergyProduced = co2Extracted / 6

    when(health.value) thenReturn(100)
    when(world.extractCO2(30)) thenReturn(co2Extracted)

    proto.metabolize()

    proto.energy should equal (initialEnergy + expectedEnergyProduced)
  }

}
