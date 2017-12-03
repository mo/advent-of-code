import org.junit.Assert._
import org.junit.Test

class Day03Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(0, Day03.part1(1))
    assertEquals(3, Day03.part1(12))
    assertEquals(2, Day03.part1(23))
    assertEquals(31, Day03.part1(1024))
  }

  @Test
  def morePart1Examples(): Unit = {
    assertEquals(0, Day03.part1(1))
    assertEquals(1, Day03.part1(2))
    assertEquals(2, Day03.part1(3))
    assertEquals(1, Day03.part1(4))
    assertEquals(2, Day03.part1(5))
    assertEquals(1, Day03.part1(6))
    assertEquals(2, Day03.part1(7))
    assertEquals(1, Day03.part1(8))
    assertEquals(2, Day03.part1(9))
    assertEquals(3, Day03.part1(10))
    assertEquals(2, Day03.part1(11))
    assertEquals(3, Day03.part1(12))
    assertEquals(4, Day03.part1(13))
    assertEquals(3, Day03.part1(14))
    assertEquals(2, Day03.part1(15))
    assertEquals(3, Day03.part1(16))
    assertEquals(4, Day03.part1(17))
    assertEquals(3, Day03.part1(18))
    assertEquals(2, Day03.part1(19))
    assertEquals(3, Day03.part1(20))
    assertEquals(4, Day03.part1(21))
    assertEquals(3, Day03.part1(22))
    assertEquals(2, Day03.part1(23))
    assertEquals(3, Day03.part1(24))
    assertEquals(4, Day03.part1(25))
    assertEquals(5, Day03.part1(26))
    assertEquals(4, Day03.part1(27))
    assertEquals(3, Day03.part1(28))

    assertEquals(4, Day03.part1(53))
    assertEquals(7, Day03.part1(64))
    assertEquals(8, Day03.part1(81))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(2, Day03.part2(1))
    assertEquals(4, Day03.part2(2))
    assertEquals(4, Day03.part2(3))
    assertEquals(5, Day03.part2(4))
    assertEquals(10, Day03.part2(5))
    assertEquals(10, Day03.part2(9))
    assertEquals(11, Day03.part2(10))
    assertEquals(23, Day03.part2(21))
    assertEquals(23, Day03.part2(22))
    assertEquals(25, Day03.part2(23))
    assertEquals(25, Day03.part2(24))
    assertEquals(26, Day03.part2(25))
    assertEquals(54, Day03.part2(26))
    assertEquals(57, Day03.part2(54))
    assertEquals(59, Day03.part2(57))
    assertEquals(122, Day03.part2(59))
    assertEquals(133, Day03.part2(122))
    assertEquals(142, Day03.part2(133))
    assertEquals(147, Day03.part2(142))
    assertEquals(304, Day03.part2(147))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((371, 369601), Day03.solve())

}
