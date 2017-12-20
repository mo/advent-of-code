import org.junit.Assert._
import org.junit.Test

class Day13Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(24, Day13.part1("""0: 3
                                   |1: 2
                                   |4: 4
                                   |6: 4""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(10, Day13.part2("""0: 3
                                   |1: 2
                                   |4: 4
                                   |6: 4""".stripMargin))
  }

  @Test
  def verifyMoreExamplesPart2Test(): Unit = {
    assertEquals(1, Day13.part2("""0: 3
                                  |1: 3""".stripMargin))
    assertEquals(1, Day13.part2("""0: 3
                                  |1: 3""".stripMargin))
    assertEquals(1, Day13.part2("""0: 7
                                  |1: 5""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((1632, 3834136), Day13.solve())

}
