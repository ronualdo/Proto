package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}
import org.jmock.Expectations.returnValue

class ProtoTest extends FixtureWordSpec 
    with JMockCycleFixture
    with ShouldMatchers {

  "A proto" when {
    val movingSpeed = 3

    "following his brain order to stand still" should {
      
      val standingBrain = new Brain {
        def process(signs: Int*): Action = Stop()
      }
      
      "stay in the same position after living" in {
        mockCycle => import mockCycle._
        
        val world = mock[World]
        val initialPosition = new PlaneCoordinates(10, 10, 100, 100)
        val proto = new Proto(standingBrain, world, initialPosition) {
          override def breathe() {
            //empty method
          }

          override def energy() = {
            0
          }
        }
        
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

      "decrement his y position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = new PlaneCoordinates(10, 5, 100, 100)
        val proto = new Proto(northMovingBrain, world, initialPosition){
          override def breathe() {}
          override def energy() = {0}
        }

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (initialPosition.decrementY(movingSpeed)) 
        }
      }
    }

    "following his brain order to move south" should {
      val southMovingBrain = new Brain {
        def process(signs: Int*) = MoveSouth(movingSpeed)
      }

      val worldHeight = 600
      
      "increment his y position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = new PlaneCoordinates(10, 5, 100, 100)
        val proto = new Proto(southMovingBrain, world, initialPosition) {
          override def breathe() {}
          override def energy() = {0}
        }

        expecting { expectation => import expectation._
          allowing (world).height; will(returnValue(worldHeight))
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (initialPosition.incrementY(movingSpeed))
        }
      }
    }

    "following his brain order to move east" should {
      val eastMovingBrain = new Brain {
        def process(signs: Int*) = MoveEast(movingSpeed)
      }
      
      val worldWidth = 800

      "increment his x position" in {
        mockCycle => import mockCycle._

        val world = mock[World]
        val initialPosition = new PlaneCoordinates(10, 5, 100, worldWidth)
        val proto = new Proto(eastMovingBrain, world, initialPosition) {
          override def breathe() {}
          override def energy() = {0}
        }

        expecting { expectation => import expectation._
          allowing (world).width; will(returnValue(worldWidth))
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (initialPosition.incrementX(movingSpeed))
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
        val initialPosition = new PlaneCoordinates(10, 5, 100, 100)
        val proto = new Proto(westMovingBrain, world, initialPosition) {
          override def breathe() {}
          override def energy() = {0}
        }

        expecting { expectation => import expectation._
          ignoring(world)
        }

        whenExecuting {
          proto.live
          proto.position should equal (initialPosition.decrementX(movingSpeed))
        }
      }
    }

  }
}
