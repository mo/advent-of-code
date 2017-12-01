import Day01.{FacingDirection, Position}
import org.junit.Test
import org.junit.Assert._

class Day01Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(5, Day01.stepsMoved(Position(0, 0, FacingDirection.NORTH), "R2, L3".split(",").map(_.trim).toList))
    assertEquals(2, Day01.stepsMoved(Position(0, 0, FacingDirection.NORTH), "R2, R2, R2".split(",").map(_.trim).toList))
    assertEquals(12, Day01.stepsMoved(Position(0, 0, FacingDirection.NORTH), "R5, L5, R5, R3".split(",").map(_.trim).toList))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(4, Day01.stepsToFirstVisitedTwice(Position(0, 0, FacingDirection.NORTH), "R8, R4, R4, R8".split(",").map(_.trim).toList))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day01.solve()
    assertEquals(161, part1)
    assertEquals(110, part2)
  }

}
