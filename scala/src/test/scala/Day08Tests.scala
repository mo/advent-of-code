import org.junit.Test
import org.junit.Assert._

class Day08Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(2 - 0,  Day08.charactersOfCodeMinusCharactersInMemory("""       ""              """.trim))
    assertEquals(5 - 3,  Day08.charactersOfCodeMinusCharactersInMemory("""       "abc"           """.trim))
    assertEquals(10 - 7, Day08.charactersOfCodeMinusCharactersInMemory("""       "aaa\"aaa"      """.trim))
    assertEquals(6 - 1,  Day08.charactersOfCodeMinusCharactersInMemory("""       "\x27"          """.trim))
  }

  @Test
  def verifyAdditionalExamplesPart1(): Unit = {
    println(Day08.unescape("""      "\\"         """.trim))
    assertEquals(4 - 1,   Day08.charactersOfCodeMinusCharactersInMemory("""      "\\"         """.trim))

    assertEquals(6 - 1,   Day08.charactersOfCodeMinusCharactersInMemory("""      "\x24"         """.trim))
    assertEquals(7 - 2,   Day08.charactersOfCodeMinusCharactersInMemory("""      "$\x24"        """.trim))
    assertEquals(8 - 3,   Day08.charactersOfCodeMinusCharactersInMemory("""      "$$\x24"       """.trim))
    assertEquals(10 - 2,  Day08.charactersOfCodeMinusCharactersInMemory("""      "\x24\x24"     """.trim))
    assertEquals(6 - 3,   Day08.charactersOfCodeMinusCharactersInMemory("""       "$\\$"         """.trim))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals("""       "\"\""             """.trim, Day08.escape("""       ""             """.trim))
    assertEquals("""       "\"abc\""          """.trim, Day08.escape("""       "abc"          """.trim))
    assertEquals("""       "\"aaa\\\"aaa\""   """.trim, Day08.escape("""       "aaa\"aaa"     """.trim))
    assertEquals("""       "\"\\x27\""        """.trim, Day08.escape("""       "\x27"         """.trim))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day08.solve()
    assertEquals(1371, part1)
    assertEquals(2117, part2)
  }

}
