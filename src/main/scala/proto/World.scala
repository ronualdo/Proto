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
    elements.foreach(_.live)
    currentTurn+=1
  }

  def extractOxigen(ammountExtracted: Int) = {
    if(ammountExtracted < 0) 
      throw new IllegalArgumentException("tried to extract "+ ammountExtracted)

    val oxigenExtracted = if (ammountExtracted > oxigenAmmount) {
      oxigenAmmount
    } else {
      ammountExtracted
    }

    oxigenAmmount -= oxigenExtracted
    oxigenExtracted
  }

}
