import org.junit.Test
import org.junit.Assert._

class Day03Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(2, Day03.calcVisitedHouses(">").size)
    assertEquals(4, Day03.calcVisitedHouses("^>v<").size)
    assertEquals(2, Day03.calcVisitedHouses("^v^v^v^v^v").size)
  }

  @Test
  def verifyExamplesPart2() : Unit = {
    assertEquals(3, Day03.calcVisitedHousesPart2("^v").size)
    assertEquals(3, Day03.calcVisitedHousesPart2("^>v<").size)
    assertEquals(11, Day03.calcVisitedHousesPart2("^v^v^v^v^v").size)
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (housesVisitedPart1, housesVisitedPart2) = Day03.solve()
    assertEquals(2592, housesVisitedPart1)
    assertEquals(2360, housesVisitedPart2)
  }

}
