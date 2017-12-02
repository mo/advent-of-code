import org.junit.Test
import org.junit.Assert._

class Day01Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(3, Day01.part1("1122"))
    assertEquals(4, Day01.part1("1111"))
    assertEquals(0, Day01.part1("1234"))
    assertEquals(9, Day01.part1("91212129"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(6, Day01.part2("1212"))
    assertEquals(0, Day01.part2("1221"))
    assertEquals(4, Day01.part2("123425"))
    assertEquals(12, Day01.part2("123123"))
    assertEquals(4, Day01.part2("12131415"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day01.solve()
    assertEquals(1031, part1)
    assertEquals(1080, part2)
  }

}
