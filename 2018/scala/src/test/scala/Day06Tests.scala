import org.junit.Assert._
import org.junit.Test

class Day06Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(17, Day06.part1(
      """1, 1
        |1, 6
        |8, 3
        |3, 4
        |5, 5
        |8, 9""".stripMargin
    ))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(16, Day06.part2(32,
      """1, 1
        |1, 6
        |8, 3
        |3, 4
        |5, 5
        |8, 9""".stripMargin
    ))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day06.solve()
    assertEquals(3223, part1)
    assertEquals(40495, part2)
  }

}
