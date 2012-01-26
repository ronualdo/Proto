package proto.domain.entity

class Health(private var _value: Int, maxValue: Int, recoverySpeed: Int) {

  def heal() {
    _value = if ( _value+recoverySpeed >= maxValue) {
      maxValue
    } else {
      _value+recoverySpeed
    }
  }

  def value = _value

  def inflictDamage(damage: Int) {
    _value -= damage
  }


}
