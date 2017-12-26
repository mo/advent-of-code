import org.junit.Assert._
import org.junit.Test

class Day20Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(0, Day20.part1("""p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>
                                  |p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>""".stripMargin))
  }

  @Test
  def verifyMoreExamplesPart1(): Unit = {
    assertEquals(1, Day20.part1("""p=<1,0,0>, v=<5,0,0>, a=<0,0,0>
                                  |p=<2,0,0>, v=<1,0,0>, a=<0,0,0>""".stripMargin))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(1, Day20.part2("""p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>
                                  |p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>
                                  |p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>
                                  |p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((364, 420), Day20.solve())

}
