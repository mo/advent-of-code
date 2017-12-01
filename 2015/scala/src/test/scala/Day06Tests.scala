import org.junit.Test
import org.junit.Assert._

class Day06Tests {

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (numLightsOn, totalBrightness) = Day06.solve()
    assertEquals(569999, numLightsOn)
    assertEquals(17836115, totalBrightness)
  }

}
