import Day14.{Grid, hexToBinary}
import org.junit.Assert._
import org.junit.Test

class Day14Tests {

  @Test
  def verifyBasicsPart1(): Unit = {
    assertEquals(Day14.popcount(0), 0)
    assertEquals(Day14.popcount(1), 1)
    assertEquals(Day14.popcount(2), 1)
    assertEquals(Day14.popcount(3), 2)
    assertEquals(Day14.popcount(4), 1)
    assertEquals(Day14.popcount(5), 2)
    assertEquals(Day14.popcount(6), 2)
    assertEquals(Day14.popcount(7), 3)
    assertEquals(Day14.popcount(127), 7)
    assertEquals(Day14.popcount(254.toByte), 7)
    assertEquals(Day14.popcount(255.toByte), 8)
    assertEquals(Day14.popcountHexStr("f"), 4)
    assertEquals(Day14.popcountHexStr("fe"), 7)
    assertEquals("0000", hexToBinary("0"))
    assertEquals("1111", hexToBinary("f"))
    assertEquals("0001001111111110", hexToBinary("13fe"))
  }

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals("1010000011000010000000010111", hexToBinary("a0c2017"))

    val grid = Day14.parse("flqrgnkx")
      .map(hexToBinary(_).map {
        case '1' => '#'
        case '0' => '.'
      })
    assertEquals("##.#.#..", grid(0).slice(0, 8))
    assertEquals(".#.#.#.#", grid(1).slice(0, 8))
    assertEquals("....#.#.", grid(2).slice(0, 8))

    assertEquals(8108, Day14.part1("flqrgnkx"))
  }

  def createFreeGrid(): Grid = (0 to 127).map(row => (0 to 127).map(col => false).toArray).toArray

  @Test
  def verifyPart2Basics(): Unit = {
    val grid = createFreeGrid()
    grid(1)(1) = true
    grid(1)(2) = true
    grid(1)(3) = true
    grid(2)(1) = true
    grid(3)(1) = true
    assertEquals(Some((1,1)), Day14.findFirstUsedSquare(grid))
    Day14.unsetRegion(grid, 1, 1)
    assertEquals(None, Day14.findFirstUsedSquare(grid))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(1242, Day14.part2("flqrgnkx"))
  }

  @Test
  def verifyMoreExamplesPart2Test(): Unit = {
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((8148, 1180), Day14.solve())

}
