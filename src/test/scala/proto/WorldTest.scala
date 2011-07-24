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
      val world = new World(initialOxigenAmmount=1000)
      val initialTurn = world.turn
      world.executeNewCycle

      world.turn should equal(initialTurn+1)
    }
  }

  "render all elements in the world" in {
    mockCycle => import mockCycle._

    val renderer = mock[Renderer]
    val proto1 = new Proto
    val proto2 = new Proto

    expecting { expectation => import expectation._
      oneOf (renderer).render(proto1)
      oneOf (renderer).render(proto2)
    }

    whenExecuting {
      val world = new World(initialOxigenAmmount=1000)
      world.add(proto1)
      world.add(proto2)
      world.renderUsing(renderer)
    }
  }
  
  "make all protos live during a world cycle" in {
    mockCycle => import mockCycle._
    val proto = mock[Proto]

    expecting { expectation => import expectation._
      oneOf (proto).live
      allowing (proto).oxigenUse
    }

    whenExecuting {
      val world = new World(initialOxigenAmmount=1000)
      world.add(proto)
      world.executeNewCycle
    }
  }
  
  "should decrease his amount of oxigen by the amount of oxigen consumed by the protos" in {
    mockCycle => import mockCycle._
    val proto1 = new Proto
    val proto2 = new Proto
    val world = new World(initialOxigenAmmount=1000)
    
    val expectedOxigenAmmount = world.oxigen - 
        (proto1.oxigenUse + proto2.oxigenUse)
    
    world.add(proto1)
    world.add(proto2)

    world.executeNewCycle

    world.oxigen should equal (expectedOxigenAmmount)
  }
}
