package proto.domain.entity

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}
import org.jmock.Expectations._

class ProtoTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {

  "A proto" should {
    "decrement its energy by its metabolism cost after metabolizing" in { () =>
      val metabolismCost = 10
      
      val proto = new Proto(metabolismCost) {
        def breathe() = 0
      }

      val initialEnergy = proto.energy

      proto.metabolize

      proto.energy should equal (initialEnergy - metabolismCost)
    }

    "breathe to produce energy during metabolizing" in {
      mockCycle => import mockCycle._
      val metabolismCost = 10
      val energyProduced = 25
      
      val proto = new Proto(metabolismCost) {
        def breathe() = energyProduced
      }

      proto.metabolize

      proto.energy should equal (energyProduced-metabolismCost)  
    }
  }
}
