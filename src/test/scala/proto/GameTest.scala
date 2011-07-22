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
      
      expecting { expectation => import expectation._
        exactly(3).of (world).executeNewCycle
      }

      whenExecuting{
        val game = new Game(world)
        game.start
        Thread.sleep(300)
        game.stop
      }
    }
  }
}
