package proto

trait Brain {
  
  def process(signs: Int*): Action

}
