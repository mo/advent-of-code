import scala.annotation.tailrec

object Day14 {

  type Grid = Array[Array[Boolean]]

  def popcount(b: Byte): Int = (0 to 7).map(i => b >>> i & 1).sum
  def popcountHexStr(str: String): Int = {
    str.map(ch => Integer.parseInt(ch.toString, 16).toByte).map(popcount).sum
  }

  def parse(input: String): IndexedSeq[String] = {
    (0 to 127)
      .map(y => input + "-" + y)
      .map(Day10.knotHashFull)
  }

  def hexToBinary(hexString: String): String = {
    hexString
      .map(ch => Integer.parseInt(ch.toString, 16))
      .flatMap(nibble => ("0000" + nibble.toBinaryString).takeRight(4))
      .mkString
  }

  def findFirstUsedSquare(grid: Grid): Option[(Int, Int)] = {
    (0 to 127).foreach(row =>
      grid(row).indexWhere(_ == true) match {
        case -1 => None
        case col => return Some(row, col)
      }
    )
    None
  }

  def unsetRegion(grid: Grid, targetRow: Int, targetCol: Int): Unit = {
    @tailrec
    def impl(grid: Grid, rowColsToUnset: List[(Int, Int)]): Unit = {
      rowColsToUnset match {
        case Nil => 0
        case (row, col) :: rowColsTail =>
          if (row >= 0 && row <= 127 && col >= 0 && col <= 127 && grid(row)(col)) {
            grid(row)(col) = false
            impl(grid, rowColsTail ++ List((row - 1, col), (row + 1, col), (row, col - 1), (row, col + 1)))
          } else {
            impl(grid, rowColsTail)
          }
      }
    }
    impl(grid, List((targetRow, targetCol)))
  }

  def part1(input: String): Int = parse(input).map(popcountHexStr).sum
  def part2(input: String): Int = {
    val grid = parse(input)
      .map(hexToBinary(_).map {
        case '1' => true
        case _ => false
      }.toArray).toArray

    var regionsFound = 0
    var done = false
    while (!done) {
      val usedSquare = findFirstUsedSquare(grid)
      usedSquare match {
        case None =>
          done = true
        case Some((row, col)) =>
          regionsFound += 1
          unsetRegion(grid, row, col)
      }
    }
    regionsFound
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day14.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
