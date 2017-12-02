object Day02 {

  def part1(input: String): Int = {
    input
      .split("\n")
      .map(line => line.split("\\s+").map(_.toInt))
      .foldLeft(0)((acc, item) => acc + item.max - item.min)
  }

  def part2(input: String): Int = {
    input
      .split("\n")
      .map(_.split("\\s+").map(_.toInt))
      .map(_.combinations(2).find(arr => arr.max % arr.min == 0).get)
      .map(arr => arr.max / arr.min)
      .sum
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
