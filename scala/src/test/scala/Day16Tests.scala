import org.junit.Test
import org.junit.Assert._

class Day16Tests {

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day16.solve()
    assertEquals(373, part1)
    assertEquals(260, part2)
  }


}
