object Day03 {

  type Claim = (Int, Int, Int, Int, Int)

  def parse(input: String): List[Claim] = {
    val claimLine = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".r
    input.split("\n").map {
      case claimLine(id, x, y, width, height) => (id.toInt, x.toInt, y.toInt, width.toInt, height.toInt)
    }.toList
  }

  def squareOverlap(claims: List[Claim]): Array[Array[Int]] = {
    val squares = Array.ofDim[Int](1000,1000)
    claims.foreach {
      case (_, x, y, width, height) =>
        (0 until height).foreach(rowIdx =>
          (0 until width).foreach(colIdx =>
            squares(y + rowIdx)(x + colIdx) += 1
          )
        )
    }
    squares
  }

  def part1(input: String): Int = squareOverlap(parse(input)).map(row => row.count(_ > 1)).sum
  def part2(input: String): Int = {
    val claims = parse(input)
    val squares = squareOverlap(claims)
    claims.find {
      case (_, x, y, width, height) =>
        (0 until height).forall(rowIdx =>
          (0 until width).forall(colIdx =>
            squares(y + rowIdx)(x + colIdx) == 1
          )
        )
    }.get._1
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day03.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
