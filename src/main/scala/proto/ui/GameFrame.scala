package proto.ui

import java.awt.Color

import scala.swing.RichWindow.Undecorated

import proto.{Game, Proto, Renderer}
import proto.domain.World
import proto.ui.swing.DirectRenderingFrame

class GameFrame extends DirectRenderingFrame 
    with Undecorated 
    with Renderer {
  
  implicit def proto2SwingProto(proto: Proto) = new SwingProto(proto)
  
  background = Color.BLACK

  def render(proto: Proto) {
    renderGraphics(proto.drawFunction)
  }

  def show() {
    paintScreen
  }

}
