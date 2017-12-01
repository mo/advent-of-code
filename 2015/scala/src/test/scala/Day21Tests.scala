import org.junit.Test
import org.junit.Assert._

class Day21Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    val examplePlayer = new Day21.Character("player", 8, 5, 5)
    val exampleBoss = new Day21.Character("boss", 12, 7, 2)
    val playerWon = Day21.fight(examplePlayer, exampleBoss)
    assertTrue(playerWon)
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day21.solve()
    assertEquals(111, part1)
    assertEquals(188, part2)
  }

}
