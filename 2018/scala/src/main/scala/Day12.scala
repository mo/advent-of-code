object Day12 {

  def part1(generations: Long, input: String): Int = {
    val initialLine = """initial state: ([#\.]+)""".r
    val initialState = (0, initialLine.findFirstMatchIn(input).get.group(1))
    val transformLine = """([#\.]{5}) => ([#\.])""".r
    val transforms = input.split("\n").flatMap {
      case transformLine(from, to) => Some(from -> to)
      case _ => None
    }.toMap.withDefault(_ => ".")

    println(initialState._1 + " " + initialState._2)
    val (finalLeftmostPotNumber, finalState) = (1L to generations).foldLeft(initialState)((state, _) => {
      val newPots = ("...." + state._2 + "....").sliding(5).map(transforms).mkString
      val initialDotCount = newPots.indexWhere(_ != '.')
      val (_, newPots2) = newPots.splitAt(initialDotCount)
      val newLeftmostPotNumber = state._1 - 2 + initialDotCount
      println(newLeftmostPotNumber + " " + newPots2)
      (newLeftmostPotNumber, newPots2)
    })

    finalState.zipWithIndex.flatMap {
      case ('#', idx) => Some(finalLeftmostPotNumber + idx)
      case _ => None
    }.sum
  }
  def part2(input: String): Int = part1(50000000000L, input)

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day12.txt").mkString
    (part1(20, input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }
}
