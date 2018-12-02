import org.junit.Test
import org.junit.Assert._

class Day01Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(3, Day01.part1(List(1, -2, 3, 1)))
    assertEquals(3, Day01.part1(List(1, 1, 1)))
    assertEquals(0, Day01.part1(List(1, 1, -2)))
    assertEquals(-6, Day01.part1(List(-1, -2, -3)))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(0, Day01.part2(List(1, -1)))
    assertEquals(10, Day01.part2(List(3, 3, 4, -2, -4)))
    assertEquals(5, Day01.part2(List(-6, 3, 8, 5, -6)))
    assertEquals(14, Day01.part2(List(7, 7, -2, -7, -4)))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day01.solve()
    assertEquals(437, part1)
    assertEquals(655, part2)
  }

}
