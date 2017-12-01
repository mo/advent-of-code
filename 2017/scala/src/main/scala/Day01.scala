object Day01 {

  def sequenceValuePart1(input: String): Int = {
    (input + input(0)).sliding(2).map(s => {
      if (s(0) == s(1)) { s(0).toString.toInt } else { 0 }
    }).sum
  }

  def sequenceValuePart2(input: String): Int = {
    (input + input).sliding(input.length / 2 + 1).take(input.length).map(s => {
      if (s.take(1) == s.takeRight(1)) { s(0).toString.toInt } else { 0 }
    }).sum
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day01.txt").mkString
    val part1 = sequenceValuePart1(input)
    val part2 = sequenceValuePart2(input)
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
