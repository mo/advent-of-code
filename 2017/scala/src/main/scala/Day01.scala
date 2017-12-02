object Day01 {

  def sumSame(input: String, matchDistance: Int): Int = {
    input
      .zipWithIndex
      .filter { case (ch, idx) => ch == input((idx + matchDistance) % input.length) }
      .map { case (ch, idx) => ch.asDigit }
      .sum
  }

  def part1(input: String): Int = sumSame(input, 1)
  def part2(input: String): Int = sumSame(input, input.length / 2)

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day01.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
