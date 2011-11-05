package proto.domain.entity

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}
import org.jmock.Expectations._

class ProtoTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {

  "A proto" when {
    "alive" should {
      "decrement its energy by its metabolism cost after metabolizing" in { () =>
        val metabolismCost = 10
        val initialEnergy = 10
        val proto = new Proto(
          initialHealth=0,
          maxHealth=100,
          recoverySpeed=1,
          initialEnergy, 
          metabolismCost
        ) {
          def breath = 0
        }

        proto.metabolize

        proto.energy should equal (initialEnergy - metabolismCost)
      }


      "breathe to produce energy during metabolizing" in { () =>
        val metabolismCost = 10
        val energyProduced = 25
        val initialEnergy = 10

        val proto = new Proto(
          initialHealth = 0,
          maxHealth = 100,
          recoverySpeed = 1,
          initialEnergy, 
          metabolismCost
        ) {
          def breath() = energyProduced
        }

        proto.metabolize

        proto.energy should equal (initialEnergy+energyProduced-metabolismCost)  
      }

      "decrement its health by the diference when metabolismCost is greater then actual energy" in { () =>
        val metabolismCost = 30
        val energy = 5
        val initialHealth = 10

        val proto = new Proto(
          initialHealth = initialHealth,
          maxHealth = 10,
          recoverySpeed = 1,
          initialEnergy = energy, 
          baseMetabolismCost = metabolismCost
        ) {
          def breath() = 0
        }

        proto.metabolize

        proto.health should equal( initialHealth + (energy-metabolismCost))
      }

      "increment its health by recoverySpeed after metabolizing" in {  () =>
        val recoverySpeed = 2
        val maxHealth = 5
        val initialHealth = 1

        val proto = new Proto(
            initialHealth,
            maxHealth,
            recoverySpeed,
            initialEnergy = 100, 
            baseMetabolismCost = 0
        ) {
          def breath = 100
        }

        proto.metabolize

        proto.health should equal (initialHealth + recoverySpeed)
      }
    }
  }

  "A proto" when {
    "not alive" should {
      "not breathe to produce energy after metabolizing" in { () =>
        val proto = new Proto(
          initialHealth = 0,
          maxHealth = 100,
          recoverySpeed = 1,
          initialEnergy = 0,
          baseMetabolismCost = 0
        ) {
          def breath = 100

          override def isAlive = false
        }

        proto.metabolize

        proto.energy should equal (0)
      }

      "not decrement its energy after metabolizing" in { () =>
        val proto = new Proto(
          initialHealth = 0,
          maxHealth = 0,
          recoverySpeed = 1,
          initialEnergy = 0,
          baseMetabolismCost = 100
        ) {
          def breath = 100

          override def isAlive = false
        }

        proto.metabolize

        proto.energy should equal (0)
      }
    }
  }
}
