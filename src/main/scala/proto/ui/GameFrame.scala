package proto.ui

import java.awt.Color

import scala.swing.RichWindow.Undecorated

import proto.ui.swing.DirectRenderingFrame

class GameFrame extends DirectRenderingFrame 
    with Undecorated 
    with Renderer {

  background = Color.BLACK

  def render(entity: Renderable) {
    renderGraphics(entity.drawFunction)
  }

  def show() {
    paintScreen
  }

}
