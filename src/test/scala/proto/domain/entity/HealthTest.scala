package proto.domain.entity

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class HealthTest extends WordSpec
    with ShouldMatchers {
    
  "Health" should {
    "be able to heal at its recovery speed rate" in {
      val health = new Health(initialValue=10, maxValue=100, recoverySpeed=2)
      health.heal

      health.value should equal (12)
    }

    "not heal beyond its maxValue" in {
      val health = new Health(initialValue=98, maxValue=100, recoverySpeed=100)
      health.heal

      health.value should equal (100)
    }
  }

}
