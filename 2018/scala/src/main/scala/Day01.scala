import scala.collection.mutable

object Day01 {

  def part1(input: List[Int]): Int = input.sum
  def part2(input: List[Int]): Int = {
    val frequencies = Stream.continually(input.toStream)
      .flatten
      .scanLeft(0)((acc, v) => acc + v)
    val seen = mutable.Set[Int]()
    frequencies.filter(x => !seen.add(x)).head
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day01.txt").mkString.split("\n").map(_.toInt).toList
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
