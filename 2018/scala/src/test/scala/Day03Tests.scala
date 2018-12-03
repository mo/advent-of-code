import org.junit.Assert._
import org.junit.Test

class Day03Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(4, Day03.part1("""#1 @ 1,3: 4x4
                                           |#2 @ 3,1: 4x4
                                           |#3 @ 5,5: 2x2""".stripMargin))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(3, Day03.part2("""#1 @ 1,3: 4x4
                                           |#2 @ 3,1: 4x4
                                           |#3 @ 5,5: 2x2""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day03.solve()
    assertEquals(103806, part1)
    assertEquals(625, part2)
  }

}
