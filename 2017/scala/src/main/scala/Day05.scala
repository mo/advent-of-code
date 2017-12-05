import scala.collection.mutable

object Day05 {

  def mazeEscape(input: String, jumpModifier: Int => Int): Int = {
    var offset = 0
    var done = false
    var stepsTaken = 0
    val jumpData = input.split("\n").map(_.toInt).zipWithIndex.map(_.swap).map { case (a, b) => (a, Some(b))}
    val jumps = mutable.Map[Int, Option[Int]](jumpData: _*).withDefaultValue(None)
    while (!done) {
      jumps(offset) match {
        case Some(jump) =>
          jumps(offset) = Some(jumpModifier(jump))
          stepsTaken += 1
          offset += jump
        case None =>
          done = true
      }
    }
    stepsTaken
  }

  def part1(input: String): Int = mazeEscape(input, (jump) => jump + 1)
  def part2(input: String): Int = mazeEscape(input, (jump) => if (jump >= 3) { jump - 1 } else { jump + 1 })

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day05.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
