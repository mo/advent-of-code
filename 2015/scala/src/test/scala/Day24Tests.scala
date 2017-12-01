import org.junit.Test
import org.junit.Assert._

class Day24Tests {

  @Test
  def verifyExamples(): Unit = {
    val weights = List(1L, 2L, 3L, 4L, 5L, 7L, 8L, 9L , 10L, 11L)

    // part1
    assertEquals(99, Day24.calcIdealConfigQE(weights, 3))

    // part2
    assertEquals(44, Day24.calcIdealConfigQE(weights, 4))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day24.solve()
    assertEquals(10723906903L, part1)
    assertEquals(74850409L, part2)
  }

  @Test
  def verifyAdditionalTestcases(): Unit = {
    assertEquals(3, Day24.calcIdealConfigQE(List(3, 3, 3), 3))
    assertEquals(5, Day24.calcIdealConfigQE(List(1, 5, 1, 5, 1, 5), 3))
    assertEquals(5, Day24.calcIdealConfigQE(List(1, 5, 2, 4, 3, 3), 3))

    // 6  1+5  2+2+2 means passenger compartment has 1 package with QE 6
    assertEquals(6, Day24.calcIdealConfigQE(List(1, 5, 6, 2, 2, 2), 3))
  }

  @Test
  def verifyRestrictedCompositions(): Unit = {
    assertEquals(
      List(
        List(1),
        List(1)
      ), Day24.fixedLengthRestrictedCompositions(1, 1, List(1, 1, 2))
    )

    assertEquals(
      List(
        List(1, 4),
        List(2, 3),
        List(3, 2),
        List(4, 1)
      ), Day24.fixedLengthRestrictedCompositions(5, 2, List(1, 2, 3, 4))
    )

    assertEquals(
      List(
        List(1, 2),
        List(1, 2),
        List(1, 2),
        List(2, 1),
        List(2, 1),
        List(2, 1)
      ), Day24.fixedLengthRestrictedCompositions(3, 2, List(1, 1, 1, 2, 3))
    )

    assertEquals(
      List(
        List(0, 3),
        List(1, 2),
        List(1, 2),
        List(1, 2),
        List(2, 1),
        List(2, 1),
        List(2, 1),
        List(3, 0)
      ), Day24.fixedLengthRestrictedCompositions(3, 2, List(0, 1, 1, 1, 2, 3))
    )

    assertEquals(
      List(), Day24.fixedLengthRestrictedCompositions(6, 2, List(0, 1, 1, 1, 2, 3))
    )
  }

}
