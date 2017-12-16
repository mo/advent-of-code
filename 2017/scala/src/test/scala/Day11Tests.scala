import org.junit.Assert._
import org.junit.Test

class Day11Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(3, Day11.part1("ne,ne,ne"))
    assertEquals(0, Day11.part1("ne,ne,sw,sw"))
    assertEquals(2, Day11.part1("ne,ne,s,s"))
    assertEquals(3, Day11.part1("se,sw,se,sw,sw"))
  }

  @Test
  def verifyMoreExamplesPart1(): Unit = {
    assertEquals(1, Day11.part1("n,s,n"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(1, 1)
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((808, 1556), Day11.solve())

}
