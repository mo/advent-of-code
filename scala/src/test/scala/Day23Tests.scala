import org.junit.Test
import org.junit.Assert._

class Day23Tests {

  @Test
  def verifyExamplePart1(): Unit = {
    val exampleProgram =
      """
        |inc a
        |jio a, +2
        |tpl a
        |inc a
      """.trim.stripMargin
    var registers = Map("a" -> 0L, "b" -> 0L)
    registers = Day23.runProgram(exampleProgram, registers)
    assertEquals(2, registers("a"))
    assertEquals(0, registers("b"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day23.solve()
    assertEquals(170, part1)
    assertEquals(247, part2)
  }

}
