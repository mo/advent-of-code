import org.junit.Assert._
import org.junit.Test

class Day12Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(325, Day12.part1(20,
      """|initial state: #..#.#..##......###...###
         |
         |...## => #
         |..#.. => #
         |.#... => #
         |.#.#. => #
         |.#.## => #
         |.##.. => #
         |.#### => #
         |#.#.# => #
         |#.### => #
         |##.#. => #
         |##.## => #
         |###.. => #
         |###.# => #
         |####. => #
      """.stripMargin))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(66, Day12.part2("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day12.solve()
    assertEquals(2, part1)
    assertEquals(2, part2)
  }

}
