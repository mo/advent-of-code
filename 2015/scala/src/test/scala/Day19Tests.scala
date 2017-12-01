import org.junit.Test
import org.junit.Assert._

class Day19Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(4, Day19.distinctOutputs("HOH", """|H => HO
                                                    |H => OH
                                                    |O => HH""".stripMargin).size)
    assertEquals(7, Day19.distinctOutputs("HOHOHO", """|H => HO
                                                       |H => OH
                                                       |O => HH""".stripMargin).size)
  }

  @Test
  def verifyExamplePart2(): Unit = {
    val transformations = """|e => H
                             |e => O
                             |H => HO
                             |H => OH
                             |O => HH""".stripMargin
    assertEquals(3, Day19.stepsNeededToRestoreE(transformations, "HOH"))
    assertEquals(6, Day19.stepsNeededToRestoreE(transformations, "HOHOHO"))
  }

  @Test
  def verifyNonTrivialCases(): Unit = {
    // These transformations are chosen so that algorithms that start with the target molecule and then
    // apply the transformations backwards are likely to get stuck because they end up with a molecule
    // string that doesn't contain any of the transformation outputs.
    val plentyDeadEndsTransformations = """|A => AAB
                                           |B => AAB
                                           |C => AAB
                                           |D => AAB
                                           |D => GRINCH
                                           |A => GR
                                           |A => IN
                                           |B => CH
                                           |D => AA
                                           |e => DB""".stripMargin
    assertEquals(5, Day19.stepsNeededToRestoreE(plentyDeadEndsTransformations, "GRINCH"))

    val smallFindTheShortcutTransformations = """|e => A
                                                 |A => B
                                                 |B => C
                                                 |B => K
                                                 |A => K
                                                 |K => GRINCH""".stripMargin
    assertEquals(3, Day19.stepsNeededToRestoreE(smallFindTheShortcutTransformations, "GRINCH"))

    val findTheShortcutTransformations = """|e => A
                                            |A => B
                                            |B => C
                                            |C => D
                                            |D => E
                                            |E => F
                                            |F => G
                                            |G => H
                                            |H => I
                                            |I => J
                                            |J => K
                                            |I => K
                                            |H => K
                                            |G => K
                                            |F => K
                                            |E => K
                                            |D => K
                                            |C => K
                                            |B => K
                                            |A => K
                                            |K => GRINCH""".stripMargin
    assertEquals(3, Day19.stepsNeededToRestoreE(findTheShortcutTransformations, "GRINCH"))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day19.solve()
    assertEquals(576, part1)
    assertEquals(207, part2)
  }

}
