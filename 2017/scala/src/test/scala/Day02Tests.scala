import org.junit.Assert._
import org.junit.Test

class Day02Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(18, Day02.part1("""5 1 9 5
                                   |7 5 3
                                   |2 4 6 8""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(9, Day02.part2("""5 9 2 8
                                  |9 4 7 3
                                  |3 8 6 5""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((45158, 294), Day02.solve())

}
