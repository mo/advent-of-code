import org.junit.Assert._
import org.junit.Test

class Day08Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(1, Day08.part1("""b inc 5 if a > 1
                                  |a inc 1 if b < 5
                                  |c dec -10 if a >= 1
                                  |c inc -20 if c == 10""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(10, Day08.part2("""b inc 5 if a > 1
                                   |a inc 1 if b < 5
                                   |c dec -10 if a >= 1
                                   |c inc -20 if c == 10""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((3880, 5035), Day08.solve())

}
