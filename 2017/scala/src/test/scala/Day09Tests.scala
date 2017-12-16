import org.junit.Assert._
import org.junit.Test

class Day09Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(1, Day09.streamScore("{}"))
    assertEquals(6, Day09.streamScore("{{{}}}"))
    assertEquals(5, Day09.streamScore("{{},{}}"))
    assertEquals(16, Day09.streamScore("{{{},{},{{}}}}"))
    assertEquals(1, Day09.streamScore("{<a>,<a>,<a>,<a>}"))
    assertEquals(9, Day09.streamScore("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
    assertEquals(9, Day09.streamScore("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
    assertEquals(3, Day09.streamScore("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(0, Day09.garbageLength("<>"))
    assertEquals(17, Day09.garbageLength("<random characters>"))
    assertEquals(3, Day09.garbageLength("<<<<>"))
    assertEquals(2, Day09.garbageLength("<{!>}>"))
    assertEquals(0, Day09.garbageLength("<!!>"))
    assertEquals(0, Day09.garbageLength("<!!!>>"))
    assertEquals(10, Day09.garbageLength("<{o\"i!a,<{i<a>"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((17537, 7539), Day09.solve())

}
