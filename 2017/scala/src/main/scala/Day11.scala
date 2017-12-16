object Day11 {

  val opposites = List(("n", "s"), ("ne", "sw"), ("se", "nw"))
  val shortcuts = List(
    ("n", "se", "ne"),
    ("n", "sw", "nw"),
    ("ne", "nw", "n"),
    ("ne", "s", "se"),
    ("se", "n", "ne"),
    ("se", "sw", "s"),
    ("s", "ne", "se"),
    ("s", "nw", "sw"),
    ("sw", "se", "s"),
    ("sw", "n", "nw"),
    ("nw", "s", "sw"),
    ("nw", "ne", "n")
  )

  def pathLength(pathInput: List[String]): Int = {
    var path = pathInput.groupBy(identity).mapValues(_.length).withDefaultValue(0)
    var simplifiedSomething = false
    do {
      simplifiedSomething = false
      for ((dir, oppositeDir) <- opposites) {
        if (path(dir) > 0 && path(oppositeDir) > 0) {
          path = path
            .updated(dir, math.max(0, path(dir) - path(oppositeDir)))
            .updated(oppositeDir, math.max(0, path(oppositeDir) - path(dir)))
          simplifiedSomething = true
        }
      }
      for ((step1, step2, shortcut) <- shortcuts) {
        if (path(step1) > 0 && path(step2) > 0) {
          val count = math.min(path(step1), path(step2))
          path = path
            .updated(step1, path(step1) - count)
            .updated(step2, path(step2) - count)
            .updated(shortcut, path(shortcut) + count)
          simplifiedSomething = true
        }
      }
    } while (simplifiedSomething)

    path.values.sum
  }

  def part1(input: String): Int = pathLength(input.split(",").toList)
  def part2(input: String): Int = {
    val fullPath = input.split(",").toList
    (1 to fullPath.length).map(n => fullPath.slice(0, n)).map(pathLength).max
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day11.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
