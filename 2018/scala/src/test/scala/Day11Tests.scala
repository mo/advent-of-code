import org.junit.Assert._
import org.junit.Test

class Day11Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(4, Day11.power(3, 5, 8))
    assertEquals(-5, Day11.power(122, 79, 57))
    assertEquals(0, Day11.power(217, 196, 39))
    assertEquals(4, Day11.power(101, 153, 71))
    assertEquals((33, 45), Day11.largestPower(18, 3))
    assertEquals((21, 61), Day11.largestPower(42, 3))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals("90,269,16", Day11.part2("18"))
    assertEquals("232,251,12", Day11.part2("42"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day11.solve()
    assertEquals("243,68", part1)
    assertEquals("236,252,12", part2)
  }

}
