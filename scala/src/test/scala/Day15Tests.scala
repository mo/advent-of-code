import org.junit.Test
import org.junit.Assert._

class Day15Tests {

  @Test
  def verifyCompositionGenerator(): Unit = {
    assertEquals(List(List(0)), Day15.weakCompositions(0, 1))
    assertEquals(List(List(1)), Day15.weakCompositions(1, 1))
    assertEquals(List(List(2)), Day15.weakCompositions(2, 1))

    assertEquals(List(List(0, 0)), Day15.weakCompositions(0, 2))
    assertEquals(List(List(0, 1), List(1, 0)), Day15.weakCompositions(1, 2))
    assertEquals(List(List(0, 2), List(1, 1), List(2, 0)), Day15.weakCompositions(2, 2))

    assertEquals(List(List(0, 0, 0)), Day15.weakCompositions(0, 3))
    assertEquals(List(List(0, 0, 1), List(0, 1, 0), List(1, 0, 0)), Day15.weakCompositions(1, 3))
    assertEquals(
      List(
        List(0, 0, 2),
        List(0, 1, 1),
        List(0, 2, 0),
        List(1, 0, 1),
        List(1, 1, 0),
        List(2, 0, 0)
      ),
      Day15.weakCompositions(2, 3))
  }

  @Test
  def verifyExamplesPart1(): Unit = {
    val exampleIngredients = Day15.parseIngredients("""Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                                                       Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3""")
    assertEquals(62842880, Day15.calcRecipeScoreAndCalories(exampleIngredients, List(44, 56))._1)
    assertEquals(62842880, Day15.calcOptimalRecipeScore(exampleIngredients))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day15.solve()
    assertEquals(21367368, part1)
    assertEquals(1766400, part2)
  }

}
