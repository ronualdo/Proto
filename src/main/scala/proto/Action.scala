package proto

abstract class Action
  case class Stop() extends Action
  case class MoveNorth(speed: Int) extends Action
