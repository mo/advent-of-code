import org.junit.Test
import org.junit.Assert._

class Day01Tests {

  @Test
  def verifyExamplesPart1Test() {
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
  def verifyExamplesPart2Test() {
    assertEquals(1, Day01.findBasementIndex(")"))
    assertEquals(5, Day01.findBasementIndex("()())"))
  }

  @Test
  def verifyEdgeCases(): Unit = {
    assertEquals(-1, Day01.findBasementIndex("("))
  }

}
