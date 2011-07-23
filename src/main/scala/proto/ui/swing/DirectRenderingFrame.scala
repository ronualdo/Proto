package proto.ui.swing

import scala.swing._

class DirectRenderingFrame extends MainFrame {

  def renderGraphics(draw: (Graphics2D) => Unit) {
    val context = borderlessGraphicContext
    draw(context)
    context.dispose
  } 

  def paintScreen() {
    if (bufferStrategy.contentsLost) bufferStrategy.show  
  }

  override def visible_=(visible: Boolean) {
    super.visible = visible
    if (visible) peer.createBufferStrategy(2)
  }
  
  private def borderlessGraphicContext = {
    val graphicContext = bufferStrategy.getDrawGraphics
    val borderlessGraphicContext = graphicContext
        .create(peer.getInsets().right, peer.getInsets().top,
            peer.getWidth() - peer.getInsets().left,
            peer.getHeight() - peer.getInsets().bottom)
    graphicContext.dispose
    borderlessGraphicContext.asInstanceOf[Graphics2D]
  }

  private def bufferStrategy = peer.getBufferStrategy

}
