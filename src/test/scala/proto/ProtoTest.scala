package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}

class ProtoTest extends FixtureWordSpec 
    with JMockCycleFixture
    with ShouldMatchers {

  "A proto" should {
    "extract from the world the oxigen consumed" in {
      mockCycle => import mockCycle._
      val world = mock[World]
      val brain = mock[Brain]
      val proto = new Proto(brain, world)

      expecting { expectation => import expectation._
        ignoring(brain)
        oneOf (world).extractOxigen(proto.oxigenUse)
      }

      whenExecuting {
        proto.live
      }
    }
  }

  "A proto" when {
    "following his brain order to stand still" should {
      
      val standingBrain = new Brain {
        def process(signs: Int*): Action = Stop()
      }
      
      "stay in the same position after living" in {
        mockCycle => import mockCycle._
        
        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(standingBrain, world, initialPosition)
        
        expecting { expectation => import expectation._
          ignoring(world)
        }
        
        whenExecuting {
          proto.live
          proto.position should equal (initialPosition)
        }
      }
    }

    "following his brain order to move north" should {
      
      val northMovingBrain = new Brain {
        def process(signs: Int*) = MoveNorth(3)
      }

      "increment his y position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(northMovingBrain, world, initialPosition)
        val expectedPosition = (initialPosition._1, initialPosition._2+3)

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (expectedPosition) 
        }
      }
    }
  }
}
