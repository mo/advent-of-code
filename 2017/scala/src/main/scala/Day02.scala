object Day02 {

  def part1(input: String): Int = {
    input
      .split("\n")
      .map(line => line.split("\\s+").map(_.toInt))
      .map(line => line.foldLeft((Int.MaxValue, Int.MinValue))((acc, item) => (math.min(acc._1, item), math.max(acc._2, item))))
      .map {
        case (min, max) => max - min
      }.sum
  }

  def part2(input: String): Int = {
    input
      .split("\n")
      .map(line => line.split("\\s+").map(_.toInt))
      .map(line => line.combinations(2).find {
        case Array(a, b) => math.max(a, b) % math.min(a, b) == 0
      }.get)
      .map {
        case Array(a, b) => math.max(a, b) / math.min(a, b)
      }.sum
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day02.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
