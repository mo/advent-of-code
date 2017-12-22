import org.junit.Assert._
import org.junit.Test

class Day15Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(
      List(
        (1092455, 430625591),
        (1181022009, 1233683848),
        (245556042, 1431495498),
        (1744312007, 137874439),
        (1352636452, 285222916)
      ),
      Day15.generate(65, 8921, 5, _ => true, _ => true).toList
    )

    assertEquals(1, Day15.part1(65, 8921, 5))
    assertEquals(588, Day15.part1(65, 8921, 40000000))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(
      List(
        (1352636452, 1233683848),
        (1992081072, 862516352),
        (530830436, 1159784568),
        (1980017072, 1616057672),
        (740335192, 412269392)
      ),
      Day15.generate(65, 8921, 5, _ % 4 == 0, _ % 8 == 0).toList
    )
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals((612, 285), Day15.solve())

}
