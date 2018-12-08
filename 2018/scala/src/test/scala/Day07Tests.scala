import org.junit.Assert._
import org.junit.Test

class Day07Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals("CABDFE", Day07.part1(
      """Step C must be finished before step A can begin.
        |Step C must be finished before step F can begin.
        |Step A must be finished before step B can begin.
        |Step A must be finished before step D can begin.
        |Step B must be finished before step E can begin.
        |Step D must be finished before step E can begin.
        |Step F must be finished before step E can begin.""".stripMargin
    ))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(15, Day07.part2(2, node => node(0).toInt - 64,
      """Step C must be finished before step A can begin.
        |Step C must be finished before step F can begin.
        |Step A must be finished before step B can begin.
        |Step A must be finished before step D can begin.
        |Step B must be finished before step E can begin.
        |Step D must be finished before step E can begin.
        |Step F must be finished before step E can begin.""".stripMargin
    ))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day07.solve()
    assertEquals("ACHOQRXSEKUGMYIWDZLNBFTJVP", part1)
    assertEquals(985, part2)
  }

}
