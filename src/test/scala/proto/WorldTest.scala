package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}

class WorldTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {
    
  "A world" should {
    "increment his turn by 1 after executing one cycle" in {
      mockCycle => import mockCycle._
      val world = new World(null)
      val initialTurn = world.turn
      world.executeNewCycle

      world.turn should equal(initialTurn+1)
    }
  }

  "render all contained elements during a world cycle" in {
    mockCycle => import mockCycle._

    val renderer = mock[Renderer]
    val proto1 = new Proto
    val proto2 = new Proto

    expecting { expectation => import expectation._
      oneOf (renderer).render(proto1)
      oneOf (renderer).render(proto2)
    }

    whenExecuting {
      val world = new World(renderer)
      world.add(proto1)
      world.add(proto2)
      world.executeNewCycle
    }
  }
  
  "make all protos live during a world cycle" in {
    mockCycle => import mockCycle._
    val renderer = mock[Renderer]
    val proto = mock[Proto]

    expecting { expectation => import expectation._
      allowing (renderer).render(proto)
      oneOf(proto).live
    }

    whenExecuting {
      val world = new World(renderer)
      world.add(proto)
      world.executeNewCycle
    }
  }

}
