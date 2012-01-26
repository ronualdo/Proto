package proto.ui

import proto.domain.entity.Proto

trait Renderer {
  
  def render(proto: Proto)

  def show()
}
