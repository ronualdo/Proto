package proto

import proto.domain.entity.Proto

trait Renderer {
  
  def render(proto: Proto)

  def show()
}
