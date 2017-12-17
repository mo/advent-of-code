import org.junit.Assert._
import org.junit.Test

class Day12Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(6, Day12.part1("""0 <-> 2
                                  |1 <-> 1
                                  |2 <-> 0, 3, 4
                                  |3 <-> 2, 4
                                  |4 <-> 2, 3, 6
                                  |5 <-> 6
                                  |6 <-> 4, 5""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(2, Day12.part2("""0 <-> 2
                                  |1 <-> 1
                                  |2 <-> 0, 3, 4
                                  |3 <-> 2, 4
                                  |4 <-> 2, 3, 6
                                  |5 <-> 6
                                  |6 <-> 4, 5""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((380, 181), Day12.solve())

}
