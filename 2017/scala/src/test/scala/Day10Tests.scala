import org.junit.Assert._
import org.junit.Test

class Day10Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals(12, Day10.part1(5, "3,4,1,5"))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.knotHashFull(""))
    assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.knotHashFull("AoC 2017"))
    assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.knotHashFull("1,2,3"))
    assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.knotHashFull("1,2,4"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((23874, "e1a65bfb5a5ce396025fab5528c25a87"), Day10.solve())

}
