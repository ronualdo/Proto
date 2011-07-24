package proto.ui

import java.awt.Color

import scala.swing.RichWindow.Undecorated

import proto.{Game, Proto, Renderer, World}
import proto.ui.swing.DirectRenderingFrame

class GameFrame extends DirectRenderingFrame 
    with Undecorated 
    with Renderer {
  
  val game = new Game(new World(1000), this)
  background = Color.BLACK

  override def visible_=(visible: Boolean) {
    super.visible = true
    game.start
  }

  def render(proto: Proto) {
    println("rendering")
  }

  def show() {
    println("showing")
  }

}
