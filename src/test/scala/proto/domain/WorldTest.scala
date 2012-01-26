package proto.domain

import org.scalatest.WordSpec
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers

import org.mockito.Mockito._

import proto.ui.Renderer
import proto.domain.entity.Proto

class WorldTest extends WordSpec
    with ShouldMatchers
    with MockitoSugar {
    
  "A world" should {
    "increment his turn by 1 after executing one cycle" in {
      val world = new World(initialOxigenAmmount=1000, size=(800, 600))
      val initialTurn = world.turn
      world.executeNewCycle

      world.turn should equal(initialTurn+1)
    }
  }

  "render all elements in the world" in {
    val renderer = mock[Renderer]
    val proto1 = mock[Proto]

    val world = new World(initialOxigenAmmount=1000, size=(800, 600))
    world.add(proto1)
    world.renderUsing(renderer)

    verify(renderer).render(proto1)
  }
  
  "make all protos metabolize during a world cycle" in {
    val proto = mock[Proto]

    val world = new World(initialOxigenAmmount=1000, size=(800, 600))
    world.add(proto)
    world.executeNewCycle()

    verify(proto).metabolize()

  }
  
  "extract the same ammount of oxigen passed as parameter when the amount is <= the worlds oxigen" in {
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount, initialCO2Ammount=0, size=(800, 600))
    val oxigenConsumed = 1000

    val oxigenExtracted = world.extractOxigen(oxigenConsumed)
    oxigenExtracted should equal (oxigenConsumed)
    world.oxigen should equal (initialOxigenAmmount - oxigenExtracted)
  }

  "extract only the worlds oxigen ammount when the oxigen passed as argument is > the worlds oxigen ammount" in {
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount, initialCO2Ammount=0, size=(800, 600))
    val oxigenConsumed = 1200

    val oxigenExtracted = world.extractOxigen(oxigenConsumed)
    oxigenExtracted should equal (initialOxigenAmmount)
    world.oxigen should equal (0)
  }

  "extract the same ammount of co2 passed as parameter when the ammount is <= thw worlds co2" in {
    val initialOxigenAmmount = 1000
    val initialCO2Ammount = 1000
    val world = new World(initialOxigenAmmount, initialCO2Ammount, (800, 600))
    val co2Consumed = 100

    val co2Extracted = world.extractCO2(co2Consumed)
    co2Consumed should equal (co2Consumed)
    world.co2 should equal (initialCO2Ammount-co2Consumed)
  }

  "extract only the worlds co2 ammount when the co2 passed as argument is > the worlds co2 ammount" in {
    val initialCO2Ammount = 10
    val world = new World(0, initialCO2Ammount, size=(100, 100))
    val co2Consumed = 100

    val co2Extracted = world.extractCO2(co2Consumed)
    co2Extracted should equal (initialCO2Ammount)
    world.co2 should equal (0)
  }

  "not be able to extract negative oxigen ammounts" in {
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount, size=(800, 600))

    evaluating { world.extractOxigen(-1) } should produce[IllegalArgumentException]
  }

  "not be able to extract negative co2 ammounts" in {
    val world = new World(size=(800, 600))

    evaluating { world.extractCO2(-1) } should produce[IllegalArgumentException]
  }

  "be able to increase his oxigen ammount" in {
    val initialOxigenAmmount = 1000
    val world = new World(initialOxigenAmmount, 0, (800, 600))
    world.increaseOxigenBy(100)

    world.oxigen should equal (initialOxigenAmmount+100)
  }

  "not be able to increase his oxigen ammount by a negative ammount" in {
    val world = new World(size=(800, 600))

    evaluating { world.increaseOxigenBy(-1) } should produce[IllegalArgumentException]
  }

  "be able to increase his co2 ammount" in { () =>
    val initialCO2Ammount = 1000
    val world = new World(0, initialCO2Ammount, (800, 600))
    world.increaseCO2By(100)

    world.co2 should equal (initialCO2Ammount + 100)
  }

  "not be able to increase his co2 ammount by a negative ammount" in { () => 
    val world = new World(size=(800, 600))

    evaluating { world.increaseCO2By(-1) } should produce[IllegalArgumentException]
  }

  "be able to return an valid position" in { () =>
    val world = new World(size=(800, 600))

    val position = world position(10, 5)

    position.x should equal (10)
    position.y should equal (5)
  }
}
