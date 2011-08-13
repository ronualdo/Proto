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
    val movingSpeed = 3

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
        def process(signs: Int*) = MoveNorth(movingSpeed)
      }

      "increment his y position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(northMovingBrain, world, initialPosition)
        val expectedPosition = (initialPosition._1, initialPosition._2 + movingSpeed)

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (expectedPosition) 
        }
      }
    }

    "following his brain order to move south" should {
      val southMovingBrain = new Brain {
        def process(signs: Int*) = MoveSouth(movingSpeed)
      }
      
      "decrement his y position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(southMovingBrain, world, initialPosition)
        val expectedPosition = (initialPosition._1, initialPosition._2 - movingSpeed)

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (expectedPosition)
        }
      }

    }

    "following his brain order to move east" should {
      val eastMovingBrain = new Brain {
        def process(signs: Int*) = MoveEast(movingSpeed)
      }

      "increment his x position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(eastMovingBrain, world, initialPosition)
        val expectedPosition = (initialPosition._1 + movingSpeed, initialPosition._2)

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (expectedPosition)
        }
      }
    }

    "following his brain order to move west" should {
      val westMovingBrain = new Brain {
        def process(signs: Int*) = MoveWest(movingSpeed)
      }

      "decrement his x position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = (10, 5)
        val proto = new Proto(westMovingBrain, world, initialPosition)
        val expectedPosition = (initialPosition._1 - movingSpeed, initialPosition._2)

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
