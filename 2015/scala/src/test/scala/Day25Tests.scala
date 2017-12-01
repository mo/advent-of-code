import org.junit.Test
import org.junit.Assert._

class Day25Tests {

  @Test
  def verifyExample(): Unit = {
    assertEquals(20151125L * 252533 % 33554393, Day25.calcCodeForRowAndColumn(2, 1))

    // first row
    assertEquals(20151125, Day25.calcCodeForRowAndColumn(1, 1))
    assertEquals(18749137, Day25.calcCodeForRowAndColumn(1, 2))
    assertEquals(17289845, Day25.calcCodeForRowAndColumn(1, 3))
    assertEquals(30943339, Day25.calcCodeForRowAndColumn(1, 4))
    assertEquals(10071777, Day25.calcCodeForRowAndColumn(1, 5))
    assertEquals(33511524, Day25.calcCodeForRowAndColumn(1, 6))

    // first column
    assertEquals(31916031, Day25.calcCodeForRowAndColumn(2, 1))
    assertEquals(16080970, Day25.calcCodeForRowAndColumn(3, 1))
    assertEquals(24592653, Day25.calcCodeForRowAndColumn(4, 1))
    assertEquals(77061, Day25.calcCodeForRowAndColumn(5, 1))
    assertEquals(33071741, Day25.calcCodeForRowAndColumn(6, 1))

    // example NW-SE diagonal
    assertEquals(21629792, Day25.calcCodeForRowAndColumn(2, 2))
    assertEquals(1601130, Day25.calcCodeForRowAndColumn(3, 3))
    assertEquals(9380097, Day25.calcCodeForRowAndColumn(4, 4))
    assertEquals(9250759, Day25.calcCodeForRowAndColumn(5, 5))
    assertEquals(27995004, Day25.calcCodeForRowAndColumn(6, 6))
  }

  @Test
  def verifyIndexFromRowAndColumn(): Unit = {
    // first diagonal
    assertEquals(1, Day25.calcIndexFromRowAndColumn(1, 1))

    // second diagonal
    assertEquals(2, Day25.calcIndexFromRowAndColumn(2, 1))
    assertEquals(3, Day25.calcIndexFromRowAndColumn(1, 2))

    // third diagonal
    assertEquals(4, Day25.calcIndexFromRowAndColumn(3, 1))
    assertEquals(5, Day25.calcIndexFromRowAndColumn(2, 2))
    assertEquals(6, Day25.calcIndexFromRowAndColumn(1, 3))

    // fourth diagonal
    assertEquals(7, Day25.calcIndexFromRowAndColumn(4, 1))
    assertEquals(8, Day25.calcIndexFromRowAndColumn(3, 2))
    assertEquals(9, Day25.calcIndexFromRowAndColumn(2, 3))
    assertEquals(10, Day25.calcIndexFromRowAndColumn(1, 4))

    // fifth diagonal
    assertEquals(11, Day25.calcIndexFromRowAndColumn(5, 1))
    assertEquals(12, Day25.calcIndexFromRowAndColumn(4, 2))
    assertEquals(13, Day25.calcIndexFromRowAndColumn(3, 3))
    assertEquals(14, Day25.calcIndexFromRowAndColumn(2, 4))
    assertEquals(15, Day25.calcIndexFromRowAndColumn(1, 5))
  }

  @Test
  def verifyCorrectAnswer(): Unit = {
    assertEquals(9132360, Day25.solve())
  }

}
