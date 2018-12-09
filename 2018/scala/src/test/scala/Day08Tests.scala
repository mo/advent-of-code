import org.junit.Assert._
import org.junit.Test

class Day08Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(138, Day08.part1("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(66, Day08.part2("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day08.solve()
    assertEquals(44338, part1)
    assertEquals(37560, part2)
  }

}
