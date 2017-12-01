import org.junit.Test
import org.junit.Assert._

class Day05Tests {

  @Test
  def verifyExamplesPart1() : Unit = {
    assertTrue(Day05.isNicePart1("ugknbfddgicrmopn"))
    assertTrue(Day05.isNicePart1("aaa"))
    assertFalse(Day05.isNicePart1("jchzalrnumimnmhp"))
    assertFalse(Day05.isNicePart1("haegwjzuvuyypxyu"))
    assertFalse(Day05.isNicePart1("dvszwmarrgswjxmb"))
  }

  @Test
  def verifyExamplesPart2() : Unit = {
    assertFalse(Day05.containsDuplicatePair("aaa"))
    assertTrue(Day05.containsXYX("xyx"))
    assertTrue(Day05.containsXYX("abcdefeghi"))
    assertTrue(Day05.containsXYX("aaa"))

    assertTrue(Day05.isNicePart2("qjhvhtzxzqqjkmpb"))
    assertTrue(Day05.isNicePart2("xxyxx"))
    assertFalse(Day05.isNicePart2("uurcxstgmygtbstg"))
    assertFalse(Day05.isNicePart2("ieodomkazucvgmuy"))
  }

  @Test
  def verifyAdditionalCases(): Unit = {
    assertTrue(Day05.containsDuplicatePair("aaaa"))
    assertTrue(Day05.containsDuplicatePair("aaaaa"))
    assertTrue(Day05.containsDuplicatePair("baaaa"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (niceStringsSeenPart1, niceStringsSeenPart2) = Day05.solve()
    assertEquals(255, niceStringsSeenPart1)
    assertEquals(55, niceStringsSeenPart2)
  }

}
