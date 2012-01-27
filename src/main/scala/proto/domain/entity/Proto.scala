package proto.domain.entity

import proto.ui.Renderable
import java.awt.{Color, Graphics2D}
import java.awt.geom.Ellipse2D


abstract class Proto (
  _health: Health,
  protected var _energy: Int, 
  baseMetabolismCost: Int
) extends Renderable {

def energy = _energy

  def metabolize() {
    if (isAlive) {
      _health.heal
      _energy += breath()
      _energy -= metabolismCost

      if(isStarving) {
        _health.inflictDamage(-_energy)
        _energy = 0
      }
    }
  }

  def metabolismCost = baseMetabolismCost

  def breath(): Int

  def health = _health.value

  def isAlive = _health.value > 0

  def isStarving = _energy < 0

  def drawFunction(context: Graphics2D) {
    context.setPaint(color)
    context.fill(new Ellipse2D.Double(50, 50, 25, 50))
  }
  
  private def color = if (isAlive) Color.green else Color.red

}
