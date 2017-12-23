import org.junit.Assert._
import org.junit.Test

class Day18Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(4, Day18.runToFirstRcv(Day18.parse("""set a 1
                                                          |add a 2
                                                          |mul a a
                                                          |mod a 5
                                                          |snd a
                                                          |set a 0
                                                          |rcv a
                                                          |jgz a -1
                                                          |set a 1
                                                          |jgz a -2""".stripMargin)))
  }

  @Test
  def verifyMoreExamplesPart1(): Unit = {
    assertEquals(3, Day18.runToFirstRcv(Day18.parse("""set b 3
                                                      |set a b
                                                      |add a 2
                                                      |add a b
                                                      |mul a 2
                                                      |mul a b
                                                      |mod a 3
                                                      |mod a b
                                                      |snd a
                                                      |snd b
                                                      |set a 0
                                                      |rcv a
                                                      |jgz a -1
                                                      |set a 1
                                                      |jgz a -2""".stripMargin)))
    assertEquals(0, Day18.runToFirstRcv(Day18.parse("""set a 2
                                                      |add a -2
                                                      |snd a
                                                      |rcv a
                                                      |jgz a -2""".stripMargin)))
    assertEquals(60, Day18.runToFirstRcv(Day18.parse("""set a 10
                                                       |add a -20
                                                       |add a 5
                                                       |add a 5
                                                       |add a 10
                                                       |add a 10
                                                       |add a 20
                                                       |add a 20
                                                       |snd a
                                                       |rcv a
                                                       |jgz a -2""".stripMargin)))
    assertEquals(50, Day18.runToFirstRcv(Day18.parse("""set a 10
                                                       |add a -20
                                                       |add a 5
                                                       |add a 5
                                                       |jgz 0 -1
                                                       |jgz -1 -2
                                                       |jgz 1 2
                                                       |add a 10
                                                       |add a 10
                                                       |add a 20
                                                       |add a 20
                                                       |snd a
                                                       |rcv a
                                                       |jgz a -2""".stripMargin)))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(3, Day18.runTwoUntilTermination(Day18.parse("""snd 1
                                                               |snd 2
                                                               |snd p
                                                               |rcv a
                                                               |rcv b
                                                               |rcv c
                                                               |rcv d""".stripMargin)))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((3188, 7112), Day18.solve())

}
