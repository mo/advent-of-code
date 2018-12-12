object Day12 {

  def part1(generations: Long, input: String): Int = {
    val initialLine = """initial state: ([#\.]+)""".r
    var pots = initialLine.findFirstMatchIn(input).get.group(1)
    val transformLine = """([#\.]{5}) => ([#\.])""".r
    val transforms = input.split("\n").flatMap {
      case transformLine(from, to) => Some(from -> to)
      case _ => None
    }.toMap.withDefault(_ => ".")

    var gen = 1
    var leftmostPotIndex = 0
    var lastPrint = 0L
    while (gen <= generations) {
      pots = ("...." + pots + "....").sliding(5).map(transforms).mkString
      leftmostPotIndex -= 2
      val initialDotCount = pots.indexWhere(_ != '.')
      pots = pots.splitAt(initialDotCount)._2
      leftmostPotIndex += initialDotCount
      gen += 1
      if (System.currentTimeMillis() - lastPrint > 2000) {
        println("progress " + gen + " " + s"%.4f".format((100*gen.toDouble/generations)))
        lastPrint = System.currentTimeMillis()
      }
    }

    pots.zipWithIndex.flatMap {
      case ('#', idx) => Some(leftmostPotIndex + idx)
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
