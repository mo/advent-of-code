object Day25 {

  def calcIndexFromRowAndColumn(row: Long, column: Long): Long = {
    val completeDiagonals = row + column - 2
    column + completeDiagonals * (completeDiagonals + 1) / 2
  }

  def calcCodeForRowAndColumn(row: Long, column: Long): Long = {
    var code = 20151125L
    (1L to calcIndexFromRowAndColumn(row, column) - 1).foreach {
      _ =>
        code = code * 252533 % 33554393
    }
    code
  }

  def solve(): Long = {
    calcCodeForRowAndColumn(2981, 3075)
  }

  def main(args: Array[String]): Unit = {
    val part1 = calcCodeForRowAndColumn(2981, 3075)
    println(s"part1 == $part1")
  }

}
