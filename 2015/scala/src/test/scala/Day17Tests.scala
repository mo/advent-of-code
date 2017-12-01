import org.junit.Test
import org.junit.Assert._

class Day17Tests {

  @Test
  def verifyExamplePart1(): Unit = {
    val exampleContainers = List(20, 15, 10, 5, 5)

    // part 1
    assertEquals(4, Day17.calcWaysToStore(25, exampleContainers).length)

    // part 2
    val containersNeed = Day17.calcWaysToStore(25, exampleContainers).map(_.sum)
    assertEquals(3, containersNeed.count(_ == containersNeed.min))
  }

  @Test
  def verifyNextCoefficients(): Unit = {
    assertEquals(List(0, 0, 1), Day17.nextCoefficients(List(0, 0, 0)))
    assertEquals(List(0, 1, 1), Day17.nextCoefficients(List(0, 1, 0)))
    assertEquals(List(1, 0, 0), Day17.nextCoefficients(List(0, 1, 1)))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day17.solve()
    assertEquals(4372, part1)
    assertEquals(4, part2)
  }

}
