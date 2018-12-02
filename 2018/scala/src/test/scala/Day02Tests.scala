import org.junit.Test
import org.junit.Assert._

class Day02Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(12, Day02.part1("abcdef\nbababc\nabbcde\nabcccd\naabcdd\nabcdee\nababab".split("\n").toList))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals("fgij", Day02.part2("abcde\nfghij\nklmno\npqrst\nfguij\naxcye\nwvxyz".split("\n").toList))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day02.solve()
    assertEquals(8118, part1)
    assertEquals("jbbenqtlaxhivmwyscjukztdp", part2)
  }

}
