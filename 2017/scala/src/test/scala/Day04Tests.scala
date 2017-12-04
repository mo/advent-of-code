import org.junit.Assert._
import org.junit.Test

class Day04Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertTrue(Day04.isValidPassphrasePart1("aa bb cc dd ee"))
    assertFalse(Day04.isValidPassphrasePart1("aa bb cc dd aa"))
    assertTrue(Day04.isValidPassphrasePart1("aa bb cc dd aaa"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertTrue(Day04.isValidPassphrasePart2("abcde fghij"))
    assertFalse(Day04.isValidPassphrasePart2("abcde xyz ecdab"))
    assertTrue(Day04.isValidPassphrasePart2("a ab abc abd abf abj"))
    assertTrue(Day04.isValidPassphrasePart2("iiii oiii ooii oooi oooo"))
    assertFalse(Day04.isValidPassphrasePart2("oiii ioii iioi iiio"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((477, 167), Day04.solve())

}
