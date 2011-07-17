package proto

import scala.collection.mutable.ArrayBuffer

class World {
  private var currentTurn = 0
  private val elements = ArrayBuffer[Proto]()

  def turn = currentTurn

  def add(proto: Proto) {
    elements += proto
  }

  def renderUsing(renderer: Renderer) {
    elements.foreach (renderer.render(_))
  }

  def executeNewCycle() {
    elements.foreach (_.live)
    currentTurn+=1
  }
}
