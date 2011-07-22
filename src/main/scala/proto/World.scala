package proto

import scala.collection.mutable.ArrayBuffer

class World(initialOxigenAmmount: Int) {
  private var oxigenAmmount = initialOxigenAmmount
  private var currentTurn = 0
  private val elements = ArrayBuffer[Proto]()

  def turn = currentTurn

  def oxigen = oxigenAmmount

  def add(proto: Proto) {
    elements += proto
  }

  def renderUsing(renderer: Renderer) {
    elements.foreach (renderer.render(_))
  }

  def executeNewCycle() {
    val oxigenConsumed = elements.foldLeft(0) {
      (oxigen: Int, proto: Proto) => {
        proto.live
        oxigen + proto.oxigenCost
      }
    }

    oxigenAmmount -= oxigenConsumed
    currentTurn+=1
  }

}
