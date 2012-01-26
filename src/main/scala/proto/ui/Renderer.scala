package proto.ui

trait Renderer {
  
  def render(renderable: Renderable)

  def show()
}
