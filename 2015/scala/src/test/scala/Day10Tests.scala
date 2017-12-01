import org.junit.Test
import org.junit.Assert._

class Day10Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals("11", Day10.lookAndSay("1"))
    assertEquals("21", Day10.lookAndSay("11"))
    assertEquals("1211", Day10.lookAndSay("21"))
    assertEquals("111221", Day10.lookAndSay("1211"))
    assertEquals("312211", Day10.lookAndSay("111221"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day10.solve()
    assertEquals(492982, part1)
    assertEquals(6989950, part2)
  }

}
