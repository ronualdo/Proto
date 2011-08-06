package proto

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class ProtoTest extends WordSpec with ShouldMatchers {
  
  "A proto" should {
    "have his energy increased by the energy produced in proto respiration and decreased by his metabolism cost after living" in {
      val proto = new Proto(size=(10,10))
      val protoInitialEnergy = proto.energy
      proto.live
      proto.energy should equal (protoInitialEnergy - proto.metabolismCost)
    }

    "be capable of moving to another position" in {
      val proto = new Proto(size=(10, 10))
      proto.move(10,10)

      proto.position should equal ((10,10))
    }
  }

}
