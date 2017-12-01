import org.junit.Test
import org.junit.Assert._

class Day18Tests {

  @Test
  def verifyExamples(): Unit = {
    val exampleLightsStr = """
        .#.#.#
        ...##.
        #....#
        ..#...
        #.#..#
        ####..
    """
    val exampleLights = Day18.loadLights(exampleLightsStr)

    // verify first row
    assertEquals(false, exampleLights(0)(0))
    assertEquals(true,  exampleLights(1)(0))
    assertEquals(false, exampleLights(2)(0))
    assertEquals(true,  exampleLights(3)(0))
    assertEquals(false, exampleLights(4)(0))
    assertEquals(true,  exampleLights(5)(0))

    // verify first column
    assertEquals(false, exampleLights(0)(0))
    assertEquals(false, exampleLights(0)(1))
    assertEquals(true,  exampleLights(0)(2))
    assertEquals(false, exampleLights(0)(3))
    assertEquals(true,  exampleLights(0)(4))
    assertEquals(true,  exampleLights(0)(5))

    assertEquals(exampleLightsStr.split("\n").map(_.trim).filter(_ != "").mkString("\n"), Day18.saveLights(exampleLights))

    assertEquals("""|..##..
                    |..##.#
                    |...##.
                    |......
                    |#.....
                    |#.##..""".stripMargin, Day18.saveLights(Day18.animateLights(exampleLights, 1)))
    assertEquals("""|..###.
                   |......
                   |..###.
                   |......
                   |.#....
                   |.#....""".stripMargin, Day18.saveLights(Day18.animateLights(exampleLights, 2)))
    assertEquals("""|...#..
                   |......
                   |...#..
                   |..##..
                   |......
                   |......""".stripMargin, Day18.saveLights(Day18.animateLights(exampleLights, 3)))
    assertEquals("""|......
                    |......
                    |..##..
                    |..##..
                    |......
                    |......""".stripMargin, Day18.saveLights(Day18.animateLights(exampleLights, 4)))


    // part 2
    assertEquals("""|#.##.#
                    |####.#
                    |...##.
                    |......
                    |#...#.
                    |#.####""".stripMargin, Day18.saveLights(Day18.animateLightsPart2(exampleLights, 1)))
    assertEquals("""|#..#.#
                    |#....#
                    |.#.##.
                    |...##.
                    |.#..##
                    |##.###""".stripMargin, Day18.saveLights(Day18.animateLightsPart2(exampleLights, 2)))
    assertEquals("""|#...##
                    |####.#
                    |..##.#
                    |......
                    |##....
                    |####.#""".stripMargin, Day18.saveLights(Day18.animateLightsPart2(exampleLights, 3)))
    assertEquals("""|#.####
                    |#....#
                    |...#..
                    |.##...
                    |#.....
                    |#.#..#""".stripMargin, Day18.saveLights(Day18.animateLightsPart2(exampleLights, 4)))
    assertEquals("""|##.###
                    |.##..#
                    |.##...
                    |.##...
                    |#.#...
                    |##...#""".stripMargin, Day18.saveLights(Day18.animateLightsPart2(exampleLights, 5)))

  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day18.solve()
    assertEquals(814, part1)
    assertEquals(924, part2)
  }

}
