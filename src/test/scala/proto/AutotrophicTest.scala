package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}
import org.jmock.Expectations.returnValue

class AutotrophicTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {
  
  "A Autotrophic" when {
    "in a world with enough CO2" should {
      "should produce energy proportional to the CO2 consumed after living" in {
        mockCycle => import mockCycle._

        val world = new World(initialCO2Ammount=1000, size=(100, 100))
        val protoCO2Use = 8
        val brain = mock[Brain]
        val initialPosition = new PlaneCoordinates(0, 0, 100, 100)

        expecting { expectation => import expectation._
          ignoring(brain)
        }

        whenExecuting {
          val proto = new AutotrophicProto(brain, world, initialPosition, protoCO2Use)
          val expectedEnergy = proto.energy + protoCO2Use / 6
          proto.live
          proto.energy should equal (expectedEnergy)
        }
      }

      "should produce O2 proportional to the CO2 consumed after living" in {
        mockCycle => import mockCycle._
        
        val initialOxigenAmmount = 100
        val world = new World(initialOxigenAmmount, initialCO2Ammount=1000, size=(800, 600))
        val protoCO2Use = 8
        val brain = mock[Brain]
        val initialPosition = new PlaneCoordinates(0, 0, 100, 100)

        expecting { expectation => import expectation._
          ignoring(brain)
        }

        whenExecuting {
          val expectedO2 = initialOxigenAmmount + protoCO2Use/6
          val proto = new AutotrophicProto(brain, world, initialPosition, protoCO2Use)
          proto.live
          world.oxigen should equal (expectedO2)
        }
      }
    }

    "in a world with less CO2 then necessary" should {
      "should produced energy proportional to the CO2 in the world" in {
        mockCycle => import mockCycle._
        
        val protoCO2Use = 10
        val initialCO2Ammount = 4
        val world = new World(initialOxigenAmmount=0, initialCO2Ammount, size=(100, 100))
        val brain = mock[Brain]
        val initialPosition = new PlaneCoordinates(0, 0, 100, 100)

        expecting { expectation => import expectation._
          ignoring(brain)
        }

        whenExecuting {
          val proto = new AutotrophicProto(brain, world, initialPosition, protoCO2Use)
          val expectedEnergy = proto.energy + initialCO2Ammount/6
          proto.live
          proto.energy should equal (expectedEnergy)
        }
      }
    }
  }

}
