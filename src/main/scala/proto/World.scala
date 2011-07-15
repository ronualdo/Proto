package proto

import scala.collection.mutable.ArrayBuffer

class World(val renderer: Renderer) {
  private var currentTurn = 0
  private val elements = ArrayBuffer[Proto]()

  def turn = currentTurn

  def add(proto: Proto) {
    elements += proto
  }

  def executeNewCycle() {
    elements.foreach {
      (proto) => {
        renderer.render(proto)
        proto.live
      }
    }
    currentTurn+=1
  }
}
