package proto.ui

import java.awt.{Color, Graphics2D}

import proto.Proto

class SwingProto(private val proto: Proto) {
  
  val drawFunction = (graphics: Graphics2D) => {
    graphics.setColor(Color.WHITE)
    graphics.drawString("Testando...", 10, 10)
  }
}
