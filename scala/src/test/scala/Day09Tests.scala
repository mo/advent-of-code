import org.junit.Test
import org.junit.Assert._

class Day09Tests {

  @Test
  def verifyExamplePart1(): Unit = {
    val distanceSpecsPart1Examples = """London to Dublin = 464
                                        London to Belfast = 518
                                        Dublin to Belfast = 141"""
    val (shortestPath, longestPath) = Day09.calcShortestLongestPath(distanceSpecsPart1Examples)
    assertEquals(605, shortestPath)
    assertEquals(982, longestPath)
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day09.solve()
    assertEquals(141, part1)
    assertEquals(736, part2)
  }

}
