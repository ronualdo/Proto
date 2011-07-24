package proto.ui.swing

import scala.swing._

class DirectRenderingFrame extends MainFrame {
  peer.setIgnoreRepaint(true)

  def renderGraphics(draw: (Graphics2D) => Unit) {
    val context = borderlessGraphicContext
    draw(context)
    //context.dispose
  }

  def paintScreen() {
    println(bufferStrategy filter (_.contentsLost ) isEmpty)
    bufferStrategy filter (!_.contentsLost) map (_.show)
  }

  override def visible_=(visible: Boolean) {
    super.visible = visible
    if (visible) {
      peer.createBufferStrategy(2)
    }
  }
  
  private def borderlessGraphicContext = {
    val graphicContext = bufferStrategy match {
      case None => throw new IllegalStateException("BufferStrategy not set")
      case Some(strategy) => strategy.getDrawGraphics
    }

    val borderlessGraphicContext = graphicContext
        .create(peer.getInsets().right, peer.getInsets().top,
            peer.getWidth() - peer.getInsets().left,
            peer.getHeight() - peer.getInsets().bottom)
    //graphicContext.dispose
    borderlessGraphicContext.asInstanceOf[Graphics2D]
  }

  private def bufferStrategy = peer.getBufferStrategy match {
    case null => None
    case strategy => Some(peer.getBufferStrategy)
  }

}
