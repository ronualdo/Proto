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
    val proto1 = mock[Proto]

    expecting { expectation => import expectation._
      oneOf (renderer).render(proto1)
    }

    whenExecuting {
      val world = new World(initialOxigenAmmount=1000)
      world.add(proto1)
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
  
  "extract the same ammount of oxigen passed as parameter when the amount is <= the worlds oxigen" in { () =>
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount)
    val oxigenConsumed = 1000

    val oxigenExtracted = world.extractOxigen(oxigenConsumed)
    oxigenExtracted should equal (oxigenConsumed)
    world.oxigen should equal (initialOxigenAmmount - oxigenExtracted)
  }

  "extract only the worlds oxigen ammount when the oxigen passed as argument is > the worlds oxigen ammount" in { () =>
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount)
    val oxigenConsumed = 1200

    val oxigenExtracted = world.extractOxigen(oxigenConsumed)
    oxigenExtracted should equal (initialOxigenAmmount)
    world.oxigen should equal (0)
  }

  "not be able to extract negative ammounts" in { () =>
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount)

    evaluating { world.extractOxigen(-1) } should produce[IllegalArgumentException]
  }

}
