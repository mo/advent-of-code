import org.junit.Test
import org.junit.Assert._

class Day11Tests {

  @Test
  def verifyWordGeneratorBasics(): Unit = {
    assertEquals("b", Day11.nextWord("a", "abc"))
    assertEquals("c", Day11.nextWord("b", "abc"))
    assertEquals("aa", Day11.nextWord("c", "abc"))
    assertEquals("ab", Day11.nextWord("aa", "abc"))
    assertEquals("ac", Day11.nextWord("ab", "abc"))
    assertEquals("ba", Day11.nextWord("ac", "abc"))
    assertEquals("bb", Day11.nextWord("ba", "abc"))
    assertEquals("bc", Day11.nextWord("bb", "abc"))
    assertEquals("ca", Day11.nextWord("bc", "abc"))
    assertEquals("cb", Day11.nextWord("ca", "abc"))
    assertEquals("cc", Day11.nextWord("cb", "abc"))
    assertEquals("aaa", Day11.nextWord("cc", "abc"))
  }

  @Test
  def verifyFilterFunctionBasics(): Unit = {
    assertTrue(Day11.containsIncreasingStraight("abc"))
    assertFalse(Day11.containsIncreasingStraight("abd"))
    assertTrue(Day11.containsIncreasingStraight("aabca"))
    assertFalse(Day11.containsIncreasingStraight("aabac"))
    assertTrue(Day11.containsIncreasingStraight("axyza"))
    assertTrue(Day11.containsIncreasingStraight("abcdef"))
    assertFalse(Day11.containsIncreasingStraight(""))

    assertTrue(Day11.containsBadChars("iol"))
    assertTrue(Day11.containsBadChars("i"))
    assertTrue(Day11.containsBadChars("o"))
    assertTrue(Day11.containsBadChars("l"))
    assertTrue(Day11.containsBadChars("al"))
    assertTrue(Day11.containsBadChars("la"))
    assertFalse(Day11.containsBadChars("abcdefghjkmnpqrstuvwxyz"))
    assertFalse(Day11.containsBadChars("a"))
    assertFalse(Day11.containsBadChars(""))

    assertTrue(Day11.containsPairs("aabb"))
    assertFalse(Day11.containsPairs("abab"))
    assertFalse(Day11.containsPairs("aaa"))
    assertFalse(Day11.containsPairs("aaaa"))
    assertFalse(Day11.containsPairs("abccab"))
    assertFalse(Day11.containsPairs("abcccab"))
    assertFalse(Day11.containsPairs("abccccab"))
    assertFalse(Day11.containsPairs("abcddcab"))
    assertTrue(Day11.containsPairs("abccddab"))
    assertTrue(Day11.containsPairs("aabcdee"))
    assertFalse(Day11.containsPairs("aabaa"))
  }

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals("xy", Day11.nextWord("xx", Day11.ALPHABET))
    assertEquals("xz", Day11.nextWord("xy", Day11.ALPHABET))
    assertEquals("ya", Day11.nextWord("xz", Day11.ALPHABET))
    assertEquals("yb", Day11.nextWord("ya", Day11.ALPHABET))

    assertTrue(Day11.containsIncreasingStraight("hijklmmn"))
    assertTrue(Day11.containsBadChars("hijklmmn"))

    assertTrue(Day11.containsPairs("abbceffg"))
    assertFalse(Day11.containsIncreasingStraight("abbceffg"))

    assertFalse(Day11.containsPairs("abbcegjk"))

    assertEquals("abcdffaa", Day11.nextPassword("abcdefgh"))
    assertEquals("ghjaabcc", Day11.nextPassword("ghijklmn"))
  }

  @Test
  def verifyCorrectAnswers() = {
    val (part1, part2) = Day11.solve()
    assertEquals("cqjxxyzz", part1)
    assertEquals("cqkaabcc", part2)
  }

}
