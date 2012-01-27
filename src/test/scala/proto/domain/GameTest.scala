package proto.domain

import org.scalatest.WordSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers

import org.mockito.Mockito._

import proto.ui.Renderer

class GameTest extends WordSpec
    with ShouldMatchers
    with MockitoSugar {
  
  "Game" should { 
    "render, run a new world cycle and show rendered result every 100 milisseconds" in {
      val world = mock[World]
      val renderer = mock[Renderer]

      val game = new Game()(world, renderer)
      game.start
      Thread.sleep(400)
      game.stop

      verify(world, atLeast(1)).renderUsing(renderer)
      verify(world, atLeast(1)).executeNewCycle
      verify(renderer, atLeast(1)).show
    }
  }
}
