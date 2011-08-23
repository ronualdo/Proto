package proto

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class PositionTest extends WordSpec
    with ShouldMatchers {

  "A position" should {
    
    "be able to increment its X coordinate" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(11, 10, 100, 50)
      
      val newPosition = initialPosition.incrementX(1)
      newPosition should equal (expectedPosition)
    }

    "not be able to increment its X coordinate pass his max value" in {
      val initialPosition = new Position(x=10, y=10, maxXValue=100, maxYValue=50)
      val expectedPosition = new Position(x=100, y=10, maxXValue=100, maxYValue=50)

      val newPosition = initialPosition.incrementX(100)
      newPosition should equal (expectedPosition)
    }

    "be able to increment its Y coordinate" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(10, 11, 100, 50)
      
      val newPosition = initialPosition.incrementY(1)
      newPosition should equal (expectedPosition)
    }

    "not be able to increment its Y coordinate pass his max value" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(10, 50, 100, 50)
      
      val newPosition = initialPosition.incrementY(100)
      newPosition should equal (expectedPosition)
    }

    "be able to decrement its x coordinate" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(5, 10, 100, 50)

      val newPosition = initialPosition.decrementX(5)
      newPosition should equal (expectedPosition)
    }

    "not be able to decrement its x coordinate to a negative value" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(0, 10, 100, 50)

      val newPosition = initialPosition.decrementX(20)
      newPosition should equal (expectedPosition)
    }

    "be able to decrement its y coordinate" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(10, 5, 100, 50)

      val newPosition = initialPosition.decrementY(5)
      newPosition should equal (expectedPosition)
    }

    "not be able to decrement its y coordinate to a negative value" in {
      val initialPosition = new Position(10, 10, 100, 50)
      val expectedPosition = new Position(10, 0, 100, 50)

      val newPosition = initialPosition.decrementY(20)
      newPosition should equal (expectedPosition)
    }
  }
    
}
