import org.junit.Assert._
import org.junit.Test

class Day09Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(32, Day09.play(9, 25))
    assertEquals(8317, Day09.play(10, 1618))
    assertEquals(146373, Day09.play(13, 7999))
    assertEquals(2764, Day09.play(17, 1104))
    assertEquals(54718, Day09.play(21, 6111))
    assertEquals(37305, Day09.play(30, 5807))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day09.solve()
    assertEquals(408679L, part1)
    assertEquals(3443939356L, part2)
  }

}
