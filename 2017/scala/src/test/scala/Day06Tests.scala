import org.junit.Assert._
import org.junit.Test

class Day06Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(5, Day06.part1(List(0, 2, 7, 0)))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(4, Day06.part2(List(0, 2, 7, 0)))
  }

  @Test
  def verifyCorrectAnswers(): Unit =
    assertEquals((4074, 2793), Day06.solve())

}
