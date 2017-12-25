import org.junit.Assert._
import org.junit.Test

class Day19Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals("ABCDEF", Day19.traceTubes("""|     |          !
                                               |     |  +--+    !
                                               |     A  |  C    !
                                               | F---|----E|--+ !
                                               |     |  |  |  D !
                                               |     +B-+  +--+ !
                                               |                !""".stripMargin)._1)
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(38, Day19.traceTubes("""|     |          !
                                         |     |  +--+    !
                                         |     A  |  C    !
                                         | F---|----E|--+ !
                                         |     |  |  |  D !
                                         |     +B-+  +--+ !
                                         |                !""".stripMargin)._2)
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals(("HATBMQJYZ", 16332), Day19.solve())

}
