import org.junit.Assert._
import org.junit.Test

class Day05Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(5, Day05.part1("""0
                                  |3
                                  |0
                                  |1
                                  |-3""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(10, Day05.part2("""0
                                   |3
                                   |0
                                   |1
                                   |-3""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((374269, 27720699), Day05.solve())

}
