package proto.ui.swing

import scala.swing._
import java.awt.Color

class DirectRenderingFrame extends MainFrame {
  peer.setIgnoreRepaint(true)

  def renderGraphics(drawUsing: (Graphics2D) => Unit) {
    borderlessGraphicContext.map{ context =>
      drawUsing(context)
      context.dispose()
    }
  }

  private def borderlessGraphicContext = {
    bufferStrategy.map{ (strategy) =>
      val graphicContext = strategy.getDrawGraphics

      val borderlessGraphicContext = graphicContext.
        create(peer.getInsets.right, peer.getInsets.top,
        peer.getWidth - peer.getInsets.left,
        peer.getHeight - peer.getInsets.bottom)

      graphicContext.dispose()

      borderlessGraphicContext.asInstanceOf[Graphics2D]
    }
  }

  private def bufferStrategy = peer.getBufferStrategy match {
    case null => None
    case strategy => Some(strategy)
  }

  def paintScreen() {
    bufferStrategy filter (!_.contentsLost) map (_.show())
  }
  
  def clearScreen() {
    borderlessGraphicContext.map { context =>
      context.setColor(Color.black)
      context.fillRect(0, 0, peer.getWidth, peer.getHeight)
      context.dispose()
    }
  }

  override def visible_=(visible: Boolean) {
    super.visible = visible
    if (visible) {
      peer.createBufferStrategy(2)
    }
  }

}
