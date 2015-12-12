import org.junit.Test
import org.junit.Assert._

class Day02Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(58, Day02.calcNeededPaper(Day02.parseDimensions("2x3x4")))
    assertEquals(43, Day02.calcNeededPaper(Day02.parseDimensions("1x1x10")))
    assertEquals(34, Day02.calcNeededRibbon(Day02.parseDimensions("2x3x4")));
    assertEquals(14, Day02.calcNeededRibbon(Day02.parseDimensions("1x1x10")));
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (totalPaperNeeded, totalRibbonNeeded) = Day02.solve()
    assertEquals(1606483, totalPaperNeeded)
    assertEquals(3842356, totalRibbonNeeded)
  }

}
