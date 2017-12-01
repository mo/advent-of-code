import org.junit.Test
import org.junit.Assert._

class Day14Tests {

  @Test
  def verifyExamples(): Unit = {
    val comet = Day14.parseReindeer("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.")
    val dancer = Day14.parseReindeer("Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.")
    assertEquals(14, Day14.distanceTravelled(comet, 1))
    assertEquals(16, Day14.distanceTravelled(dancer, 1))
    assertEquals(140, Day14.distanceTravelled(comet, 10))
    assertEquals(160, Day14.distanceTravelled(dancer, 10))
    assertEquals(140, Day14.distanceTravelled(comet, 11))
    assertEquals(176, Day14.distanceTravelled(dancer, 11))
    assertEquals(140, Day14.distanceTravelled(comet, 12))
    assertEquals(176, Day14.distanceTravelled(dancer, 12))
    assertEquals(140, Day14.distanceTravelled(comet, 137))
    assertEquals(176, Day14.distanceTravelled(dancer, 137))
    assertEquals(154, Day14.distanceTravelled(comet, 138))
    assertEquals(176, Day14.distanceTravelled(dancer, 138))

    assertEquals(176, Day14.distanceTravelled(dancer, 173))
    assertEquals(192, Day14.distanceTravelled(dancer, 174))

    assertEquals(1120, Day14.distanceTravelled(comet, 1000))
    assertEquals(1056, Day14.distanceTravelled(dancer, 1000))

    // part 2
    assertEquals(List(("Dancer", 1), ("Comet", 0)), Day14.racePart2(List(comet, dancer), 1))
    assertEquals(List(("Dancer", 2), ("Comet", 0)), Day14.racePart2(List(comet, dancer), 2))
    assertEquals(List(("Dancer", 2), ("Comet", 0)), Day14.racePart2(List(comet, dancer), 2))
    assertEquals(List(("Dancer", 139), ("Comet", 1)), Day14.racePart2(List(comet, dancer), 140))
    assertEquals(List(("Dancer", 689), ("Comet", 312)), Day14.racePart2(List(comet, dancer), 1000))
  }

  @Test
  def verifyCorrectAnswers() {
    val (part1, part2) = Day14.solve()
    assertEquals(2660, part1)
    assertEquals(1256, part2)
  }

}
