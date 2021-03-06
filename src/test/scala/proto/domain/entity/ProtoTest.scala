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
    "be alive when his health is greater then 0" in { () =>
      val proto = new Proto(
        _health = new Health(_value=100, maxValue=100, recoverySpeed=1),
        _energy=0,
        baseMetabolismCost=0
      ) {
        def breath = 100
      }

      proto.metabolize

      proto.isAlive should equal (true)
    }
  }

  "A proto" when {
    "alive" should {
      "decrement its energy by its metabolism cost after metabolizing" in { () =>
        val metabolismCost = 10
        val initialEnergy = 10
        val proto = new Proto(
          new Health(_value=100, maxValue=100, recoverySpeed=1),
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
          new Health(_value=10, maxValue=10, recoverySpeed=1),
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
        val initialHealth = 100

        val proto = new Proto(
          new Health(_value=initialHealth, maxValue=100, recoverySpeed=1),
          _energy = energy, 
          baseMetabolismCost = metabolismCost
        ) {
          def breath() = 0
        }

        proto.metabolize

        proto.health should equal( initialHealth + (energy-metabolismCost))
      }

      "increment its health by recoverySpeed after metabolizing" in {  () =>
        val recoverySpeed = 2
        val initialHealth = 1

        val protoHealth = new Health(
          _value = initialHealth,
          maxValue = 5,
          recoverySpeed = recoverySpeed
        )

        val proto = new Proto(
            _health = protoHealth,    
            _energy = 100, 
            baseMetabolismCost = 0
        ) {
          def breath = 100
        }

        proto.metabolize

        proto.health should equal (initialHealth + recoverySpeed)
      }

      "not have his energy decremented to a negative number after metabolizing" in { () =>
        val proto = new Proto(
          _health = new Health(_value=100, maxValue=100, recoverySpeed=100),
          _energy=0,
          baseMetabolismCost=100
        ) {
          def breath = 0
        }

        proto.metabolize

        proto.energy should equal (0)
      }
    }
  }

  "A proto" when {
    "not alive" should {
      "not breathe to produce energy after metabolizing" in { () =>
        val proto = new Proto(
          _health = new Health(_value=0, maxValue=100, recoverySpeed=1),
          _energy = 0,
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
          _health = new Health(_value=0, maxValue=0, recoverySpeed=1),
          _energy = 0,
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
