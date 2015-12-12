import org.junit.Test
import org.junit.Assert._

class Day01Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(0, Day01.findFloor("(())"))
    assertEquals(0, Day01.findFloor("()()"))
    assertEquals(3, Day01.findFloor("((("))
    assertEquals(3, Day01.findFloor("(()(()("))
    assertEquals(3, Day01.findFloor("))((((("))
    assertEquals(-1, Day01.findFloor("())"))
    assertEquals(-1, Day01.findFloor("))("))
    assertEquals(-3, Day01.findFloor(")))"))
    assertEquals(-3, Day01.findFloor(")())())"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(1, Day01.findBasementIndex(")"))
    assertEquals(5, Day01.findBasementIndex("()())"))
  }

  @Test
  def verifyEdgeCases(): Unit = {
    assertEquals(-1, Day01.findBasementIndex("("))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val directions = io.Source.fromFile("data/day01.txt").mkString
    assertEquals(74, Day01.findFloor(directions))
    assertEquals(1795, Day01.findBasementIndex(directions))
  }

}
