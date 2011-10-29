package proto.domain

import proto.domain.entity.Proto
import proto.Renderer

import scala.collection.mutable.ArrayBuffer

class World(
  initialOxigenAmmount: Int = 0,
  initialCO2Ammount: Int = 0,
  size: Tuple2[Int, Int]
) 
{
  private var oxigenAmmount = initialOxigenAmmount
  private var co2Ammount = initialCO2Ammount
  private var currentTurn = 0
  private val elements = ArrayBuffer[Proto]()
  
  val width = size._1
  val height = size._2
  
  def turn = currentTurn

  def oxigen = oxigenAmmount

  def co2 = co2Ammount

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
    val availableAmmount = calculateAvailableAmmount(oxigenAmmount, ammountExtracted)
    oxigenAmmount -= availableAmmount
    availableAmmount
  }

  def extractCO2(ammountExtracted: Int) = {
    val availableAmmount = calculateAvailableAmmount(co2Ammount, ammountExtracted)
    co2Ammount -= availableAmmount
    availableAmmount
  }

  private def calculateAvailableAmmount(source: Int, ammount: Int) = {
    if(ammount < 0) {
      throw new IllegalArgumentException("tried to extract "+ammount)
    }

    val ammountExtracted = if (ammount > source) {
      source
    } else {
      ammount
    }

    ammountExtracted
  }

  def increaseOxigenBy(ammount: Int) {
    if(ammount < 0) {
      throw new IllegalArgumentException("tried to increase by "+ ammount)
    }
    oxigenAmmount += ammount
  }

  def increaseCO2By(ammount: Int) {
    if(ammount < 0) {
      throw new IllegalArgumentException("tried to increase by "+ ammount)
    }
    co2Ammount += ammount
  }

  def position(x: Int, y: Int) = {
    val xCoordinate = new Coordinate(x, width)
    val yCoordinate = new Coordinate(y, height)
    new Position(xCoordinate, yCoordinate)
  }

  class Position(xCoordinate: Coordinate, yCoordinate: Coordinate) {

    def x = xCoordinate.value
    def y = yCoordinate.value
    
    def incrementXBy(number: Int) = {
      val newX = xCoordinate+number
      new Position(newX, yCoordinate)
    }

    def incrementYBy(number: Int) = {
      val newY = yCoordinate+number
      new Position(xCoordinate, newY)
    }

    def decrementXBy(number: Int) = {
      val newX = xCoordinate-number
      new Position(newX, yCoordinate)
    }

    def decrementYBy(number: Int) = {
      val newY = yCoordinate-number
      new Position(xCoordinate, newY)
    }
  }
}
