package proto

abstract class Action
  case class Stop() extends Action
  case class MoveNorth(speed: Int) extends Action
  case class MoveSouth(speed: Int) extends Action
  case class MoveEast(speed: Int) extends Action
  case class MoveWest(speed: Int) extends Action
