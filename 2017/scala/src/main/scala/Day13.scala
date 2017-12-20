object Day13 {

  def run(input: String, delay: Int): Option[Int] = {
    if (delay % 250000 == 0) {
      println("run(delay=" + delay + ")")
    }
    val scannerRanges = input
      .split("\n")
      .map(
        _.split(": ") match {
          case Array(depth, range) => depth.toInt -> range.toInt
        }
      )
      .toMap
    val deepestLayerIdx = scannerRanges.keys.max

    // totalSeverity == None means "not caught"
    val totalSeverity: Option[Int] = (0 to deepestLayerIdx).foldLeft(None: Option[Int])((severity, packetLayerIdx) => {
      val picosecond = delay + packetLayerIdx
      scannerRanges.get(packetLayerIdx) match {
        case None =>
          severity
        case Some(range) =>
          val scannerPos = picosecond % (2 * range - 2)
          if (scannerPos == 0) {
            Some(severity.getOrElse(0) + range * packetLayerIdx)
          } else {
            severity
          }
      }
    })

    totalSeverity
  }

  def part1(input: String): Int = run(input, 0).get
  def part2(input: String): Int = Iterator.from(1).indexWhere(run(input, _).isEmpty) + 1

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day13.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
