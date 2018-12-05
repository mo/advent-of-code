import org.junit.Assert._
import org.junit.Test

class Day05Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(10, Day05.part1("dabAcCaCBAcCcaDA"))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(4, Day05.part2("dabAcCaCBAcCcaDA"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day05.solve()
    assertEquals(9386, part1)
    assertEquals(4876, part2)
  }

}
