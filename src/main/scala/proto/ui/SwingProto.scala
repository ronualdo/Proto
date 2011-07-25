package proto.ui

import java.awt.{Color, Graphics2D}
import java.awt.geom.Ellipse2D
import proto.Proto

class SwingProto(private val proto: Proto) {
  
  val drawFunction = (graphics: Graphics2D) => {
    graphics.setColor(Color.WHITE)
    val (height, width) = (10, 10)
    val (x, y) = proto.position
    graphics.draw(new Ellipse2D.Float(x, y, width, height))
  }

}
