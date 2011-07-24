package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import org.jmock.{Expectations, Mockery}

class GameTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {
    
  "The Game" should {
    "run a new world cycle every 100 miliseconds" in {
      mockCycle => import mockCycle._
      val world = mock[World]
      val renderer = mock[Renderer]

      expecting { expectation => import expectation._
        allowing (world).renderUsing(renderer)
        exactly(3).of (world).executeNewCycle
      }

      whenExecuting{
        val game = new Game(world, renderer)
        game.start
        Thread.sleep(300)
        game.stop
      }
    }

    "render the world every 100 miliseconds" in {
      mockCycle => import mockCycle._
      val world = mock[World]
      val renderer = mock[Renderer]

      expecting { expectation => import expectation._
        allowing (world).executeNewCycle
        exactly(3).of (world).renderUsing(renderer)
      }

      whenExecuting {
        val game = new Game(world, renderer)
        game.start
        Thread.sleep(300)
        game.stop
      }
    }
  }
}
