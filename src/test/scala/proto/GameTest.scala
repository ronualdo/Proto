package proto

import org.scalatest.fixture.FixtureWordSpec
import org.scalatest.mock.{JMockCycleFixture, JMockExpectations}
import org.scalatest.matchers.ShouldMatchers

import proto.domain.World

import org.jmock.{Expectations, Mockery}

class GameTest extends FixtureWordSpec
    with JMockCycleFixture
    with ShouldMatchers {
  
  "Game" should { 
    "render, run a new world cycle and show rendered result every 100 milisseconds" in {
      mockCycle => import mockCycle._
      val world = mock[World]
      val renderer = mock[Renderer]

      expecting { expectation => import expectation._
        exactly(3).of (world).renderUsing(renderer)
        exactly(3).of (world).executeNewCycle
        exactly(3).of (renderer).show
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
