import org.junit.Assert._
import org.junit.Test

class Day17Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals((1, List(0, 1)), Day17.spinlock(3, 1))
    assertEquals((1, List(0, 2, 1)), Day17.spinlock(3, 2))
    assertEquals((2, List(0, 2, 3, 1)), Day17.spinlock(3, 3))
    assertEquals((2, List(0, 2, 4, 3, 1)), Day17.spinlock(3, 4))
    assertEquals((1, List(0, 5, 2, 4, 3, 1)), Day17.spinlock(3, 5))
    assertEquals((5, List(0, 5, 2, 4, 3, 6, 1)), Day17.spinlock(3, 6))
    assertEquals((2, List(0, 5, 7, 2, 4, 3, 6, 1)), Day17.spinlock(3, 7))
    assertEquals((6, List(0, 5, 7, 2, 4, 3, 8, 6, 1)), Day17.spinlock(3, 8))
    assertEquals((1, List(0, 9, 5, 7, 2, 4, 3, 8, 6, 1)), Day17.spinlock(3, 9))

    val (pos, buffer) = Day17.spinlock(3, 2017)
    assertEquals(List(1512, 1134, 151, 2017, 638,1513, 851), buffer.slice(pos - 3, pos + 4))

    assertEquals(638, Day17.part1(3))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((1306, 20430489), Day17.solve())

}
