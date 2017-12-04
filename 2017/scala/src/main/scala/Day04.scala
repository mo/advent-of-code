object Day04 {

  def isValidPassphrasePart1(phrase: String): Boolean =
    phrase.split("\\s+").groupBy(identity).values.forall(_.length == 1)

  def isValidPassphrasePart2(phrase: String): Boolean =
    phrase.split("\\s+").map(_.sorted).groupBy(identity).values.forall(_.length == 1)

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day04.txt").mkString
    (input.split("\n").count(isValidPassphrasePart1), input.split("\n").count(isValidPassphrasePart2))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
