import org.junit.Assert._
import org.junit.Test

class Day07Tests {

  @Test
  def verifyExamplesPart1Test(): Unit = {
    assertEquals("tknk", Day07.part1("""pbga (66)
                                       |ebii (61)
                                       |havc (66)
                                       |xhth (57)
                                       |ktlj (57)
                                       |fwft (72) -> ktlj, cntj, xhth
                                       |qoyq (66)
                                       |padx (45) -> pbga, havc, qoyq
                                       |tknk (41) -> ugml, padx, fwft
                                       |jptl (61)
                                       |ugml (68) -> gyxo, ebii, jptl
                                       |gyxo (61)
                                       |cntj (57)""".stripMargin))
  }

  @Test
  def verifyExamplesPart2Test(): Unit = {
    assertEquals(60, Day07.part2("""pbga (66)
                                   |ebii (61)
                                   |havc (66)
                                   |xhth (57)
                                   |ktlj (57)
                                   |fwft (72) -> ktlj, cntj, xhth
                                   |qoyq (66)
                                   |padx (45) -> pbga, havc, qoyq
                                   |tknk (41) -> ugml, padx, fwft
                                   |jptl (61)
                                   |ugml (68) -> gyxo, ebii, jptl
                                   |gyxo (61)
                                   |cntj (57)""".stripMargin))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals(("svugo", 1152), Day07.solve())

}
