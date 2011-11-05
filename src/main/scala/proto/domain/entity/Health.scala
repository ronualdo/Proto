package proto.domain.entity

class Health(initialValue: Int, maxValue: Int, recoverySpeed: Int) {
  private var currentValue = initialValue

  def heal() {
    currentValue = if ( currentValue+recoverySpeed >= maxValue) {
      maxValue
    } else {
      currentValue+recoverySpeed
    }
  }

  def value = currentValue


}
