object Day09 {

  def streamScore(input: String): Int = {
    val enterLeave = input.replaceAll("!.", "").replaceAll("<[^>]*>", "").replaceAll(",", "").map {
      case '{' => 1
      case '}' => -1
    }
    enterLeave.scanLeft(0)(_+_).tail.zipWithIndex.map {
      case (s, i) => if (enterLeave(i) == 1) { s } else { 0 }
    }.sum
  }

  def garbageLength(input: String): Int = {
    "<([^>]*)>".r.findAllMatchIn(input.replaceAll("!.", "")).map(_.group(1).length).sum
  }

  def part1(input: String): Int = streamScore(input)
  def part2(input: String): Int = garbageLength(input)

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day09.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
