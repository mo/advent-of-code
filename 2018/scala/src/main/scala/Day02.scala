object Day02 {

  def hasLetterAppearingNTimes(n: Int)(input: String): Boolean = input.groupBy(identity).values.map(_.length).iterator.contains(n)

  def part1(input: List[String]): Int = {
    input.count(hasLetterAppearingNTimes(2)) * input.count(hasLetterAppearingNTimes(3))
  }

  def diff(a: String, b: String): Int = a.zip(b).count({ case (x, y) => x != y })
  def shared(ids: List[String]): String = ids.head.zip(ids(1)).filter({ case (x, y) => x == y }).map(_._1).mkString

  def part2(input: List[String]): String = {
    val groups = input.map(id => List(id) ++ input.filter(i => diff(i, id) == 1)).filter(_.length > 1)
    shared(groups.head)
  }

  def solve(): (Int, String) = {
    val input = DataFolder.openFile("day02.txt").mkString.split("\n").toList
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
