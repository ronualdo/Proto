package proto.domain.entity

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}
import org.jmock.Expectations._

import proto.domain.World

class AutrotrophicProtoTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {

  "An autrophic proto" should {
    
    "consum world co2 after metabolize" in {
      mockCycle => import mockCycle._
      
      val world = mock[World]
      val co2Cost = 20
      val proto = new AutotrophicProto(world, 10, co2Cost)

      expecting { expectation => import expectation._
        oneOf (world).extractCO2(co2Cost)
        allowing(world)
      }

      whenExecuting {
        proto.metabolize
      }
    }
  }

  "generate an O2 ammount relative to the CO2 ammount extracted" in {
    mockCycle => import mockCycle._

    val world = mock[World]
    val co2Extracted = 10
    val proto = new AutotrophicProto(world, 10, 20)

    expecting { expectation => import expectation._
      oneOf (world).extractCO2(20); will(returnValue(co2Extracted))
      oneOf (world).increaseOxigenBy (co2Extracted)
    }

    whenExecuting {
      proto.metabolize
    }
  }

  "generate energy in a proportial of 1/6 of the CO2 processed" in {
    mockCycle => import mockCycle._

    val world = mock[World]
    val co2Extracted = 20
    val proto = new AutotrophicProto(world, 0, 30)
    val initialEnergy = proto.energy
    val expectedEnergyProduced = co2Extracted / 6

    expecting { expectation => import expectation._
      allowing (world).extractCO2(30); will(returnValue(co2Extracted))
      allowing (world)
    }

    whenExecuting {
      proto.metabolize
    }

    proto.energy should equal (initialEnergy + expectedEnergyProduced)
  }

}
