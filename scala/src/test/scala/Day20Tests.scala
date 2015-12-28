import org.junit.Test
import org.junit.Assert._

class Day20Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(1, Day20.calcHouseThatGetsAtleastNPresentsPart1(10))

    assertEquals(2, Day20.calcHouseThatGetsAtleastNPresentsPart1(29))
    assertEquals(2, Day20.calcHouseThatGetsAtleastNPresentsPart1(30))

    assertEquals(3, Day20.calcHouseThatGetsAtleastNPresentsPart1(31))
    assertEquals(3, Day20.calcHouseThatGetsAtleastNPresentsPart1(39))
    assertEquals(3, Day20.calcHouseThatGetsAtleastNPresentsPart1(40))

    assertEquals(4, Day20.calcHouseThatGetsAtleastNPresentsPart1(41))
    assertEquals(4, Day20.calcHouseThatGetsAtleastNPresentsPart1(59))
    assertEquals(4, Day20.calcHouseThatGetsAtleastNPresentsPart1(60))
    assertEquals(4, Day20.calcHouseThatGetsAtleastNPresentsPart1(69))
    assertEquals(4, Day20.calcHouseThatGetsAtleastNPresentsPart1(70))

    assertEquals(6, Day20.calcHouseThatGetsAtleastNPresentsPart1(71))
    assertEquals(6, Day20.calcHouseThatGetsAtleastNPresentsPart1(119))
    assertEquals(6, Day20.calcHouseThatGetsAtleastNPresentsPart1(120))

    assertEquals(8, Day20.calcHouseThatGetsAtleastNPresentsPart1(121))
  }

  @Test
  def sanityCheckPart2(): Unit = {
    assertEquals(11, Day20.calcGiftsDeliveredPart2(1))
    assertEquals(11 + 22, Day20.calcGiftsDeliveredPart2(2))
    assertEquals(11 + 33, Day20.calcGiftsDeliveredPart2(3))
    assertEquals(11 + 22 + 44, Day20.calcGiftsDeliveredPart2(4))
    assertEquals(11 + 55, Day20.calcGiftsDeliveredPart2(5))
    assertEquals(11 + 22 + 33 + 66, Day20.calcGiftsDeliveredPart2(6))
    assertEquals(11 + 77, Day20.calcGiftsDeliveredPart2(7))
    assertEquals(11 + 22 + 44 + 88, Day20.calcGiftsDeliveredPart2(8))
    assertEquals(11 + 33 + 99, Day20.calcGiftsDeliveredPart2(9))
    assertEquals(11 + 22 + 55 + 110, Day20.calcGiftsDeliveredPart2(10))
    assertEquals(11 + 121, Day20.calcGiftsDeliveredPart2(11))

    assertEquals(1, Day20.calcHouseThatGetsAtleastNPresentsPart2(1))
    assertEquals(1, Day20.calcHouseThatGetsAtleastNPresentsPart2(2))
    assertEquals(1, Day20.calcHouseThatGetsAtleastNPresentsPart2(11))
    assertEquals(2, Day20.calcHouseThatGetsAtleastNPresentsPart2(12))
    assertEquals(2, Day20.calcHouseThatGetsAtleastNPresentsPart2(33))
    assertEquals(3, Day20.calcHouseThatGetsAtleastNPresentsPart2(34))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day20.solve()
    assertEquals(831600, part1)
    assertEquals(884520, part2)
  }

}
