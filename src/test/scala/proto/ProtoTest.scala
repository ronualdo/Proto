package proto

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class ProtoTest extends WordSpec with ShouldMatchers {
  
  "A proto" should {
    "have his energy decreased after living" in {
      val proto = new Proto(oxigenCost=100)
      val protoInitialEnergy = proto.energy
      proto.live
      proto.energy should be < protoInitialEnergy
    }
  }

}
