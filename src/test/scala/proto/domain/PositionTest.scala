package proto

import proto.domain.World

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class PositionTest extends WordSpec 
    with ShouldMatchers {
  
  "A position" when {
    "in a 800x600 world" should {
      val world = new World(size=(800, 600))

      "be able to increment its x coordinate" in {
        val position = world position(10, 5)
        
        val result = position incrementXBy (5)

        result.x should equal (15)
      }

      "not be able to increment its x coordinate pass 800" in {
        val position = world position(700, 10)

        val result = position incrementXBy 200

        result.x should equal (800)
      }

      "not be able to decrement its x coordinate to a negative number" in {
        val position = world position(3, 10)

        val result = position decrementXBy 5

        result.x should equal (0)
      }

      "be able to increment its y coordinate" in {
        val position = world position(10, 5)

        val result = position incrementYBy 5

        result.y should equal (10)
      }

      "not be able to increment its y coordinate pass 600" in {
        val position = world position(100, 500)

        val result = position incrementYBy 200

        result.y should equal (600)
      }

      "not be able to decrement its y coordinate to a negative number" in {
        val position = world position(100, 10)

        val result = position decrementYBy 100
        
        result.y should equal (0)
      }

      "be able to decrement its x coordinate" in {
        val position = world.position(10, 5)

        val result = position decrementXBy 5

        result.x should equal (5)
      }

      "be able to decrement its y coordinate" in {
        val position = world position(10, 5)

        val result = position decrementYBy 5

        result.y should equal (0)
      }

    }
  }

}
