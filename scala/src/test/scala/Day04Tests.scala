import org.junit.Test
import org.junit.Assert._

class Day04Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(609043, Day04.calcAdventCoinNumber(5, "abcdef"))
    assertEquals(1048970, Day04.calcAdventCoinNumber(5, "pqrstuv"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (fiveZeroNum, sixZeroNum) = Day04.solve()
    assertEquals(117946, fiveZeroNum)
    assertEquals(3938038, sixZeroNum)
  }

}
