import org.junit.Test
import org.junit.Assert._

class Day13Tests {

  @Test
  def verifyExamplePart1(): Unit = {
    val seatingPreferences =
      """Alice would gain 54 happiness units by sitting next to Bob.
         Alice would lose 79 happiness units by sitting next to Carol.
         Alice would lose 2 happiness units by sitting next to David.
         Bob would gain 83 happiness units by sitting next to Alice.
         Bob would lose 7 happiness units by sitting next to Carol.
         Bob would lose 63 happiness units by sitting next to David.
         Carol would lose 62 happiness units by sitting next to Alice.
         Carol would gain 60 happiness units by sitting next to Bob.
         Carol would gain 55 happiness units by sitting next to David.
         David would gain 46 happiness units by sitting next to Alice.
         David would lose 7 happiness units by sitting next to Bob.
         David would gain 41 happiness units by sitting next to Carol."""
    assertEquals(330, Day13.optimalSeatingHappiness(seatingPreferences, List()))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day13.solve()
    assertEquals(618, part1)
    assertEquals(601, part2)
  }

}
