import org.junit.Test
import org.junit.Assert._
import org.json4s.native.JsonMethods.parse

class Day12Tests {

  @Test
  def verifyExamplesPart1(): Unit = {
    assertEquals(6, Day12.sumAllNumbers("""    [1,2,3]                   """.trim))
    assertEquals(6, Day12.sumAllNumbers("""    {"a":2,"b":4}             """.trim))
    assertEquals(3, Day12.sumAllNumbers("""    [[[3]]]                   """.trim))
    assertEquals(3, Day12.sumAllNumbers("""    {"a":{"b":4},"c":-1}      """.trim))
    assertEquals(0, Day12.sumAllNumbers("""    {"a":[-1,1]}              """.trim))
    assertEquals(0, Day12.sumAllNumbers("""    [-1,{"a":1}]              """.trim))
    assertEquals(0, Day12.sumAllNumbers("""    []                        """.trim))
    assertEquals(0, Day12.sumAllNumbers("""    {}                        """.trim))
  }

  @Test
  def verifyExamplesPart2(): Unit = {
    assertEquals(6, Day12.sumNonRedNumbers(parse("""    [1,2,3]                            """.trim)))
    assertEquals(4, Day12.sumNonRedNumbers(parse("""    [1,{"c":"red","b":2},3]            """.trim)))
    assertEquals(0, Day12.sumNonRedNumbers(parse("""    {"d":"red","e":[1,2,3,4],"f":5}    """.trim)))
    assertEquals(6, Day12.sumNonRedNumbers(parse("""    [1,"red",5]                        """.trim)))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day12.solve()
    assertEquals(156366, part1)
    assertEquals(96852, part2)
  }

}
