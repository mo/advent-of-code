object Day04 {
  type GuardID = Int

  def parse(input: String): Map[GuardID, List[Range]] = {
    val line = """\[(\d+)-(\d+)-(\d+) (\d+):(\d+)\] (.+)""".r
    val beginShift = """Guard #(\d+) begins shift""".r
    var guardSleepRanges = Map[GuardID, List[Range]]()
    var guardId = 0
    var fellAsleepMinute = 0
    input.split("\n").sorted.foreach {
      case line(year, month, day, hour, minute, action) =>
        action match {
          case "falls asleep" =>
            fellAsleepMinute = minute.toInt
          case "wakes up" =>
            val newSleepRanges = Range(fellAsleepMinute, minute.toInt) :: guardSleepRanges.getOrElse(guardId, List.empty[Range])
            guardSleepRanges += guardId -> newSleepRanges
          case beginShift(guard) =>
            guardId = guard.toInt
        }
    }
    guardSleepRanges
  }

  def part1(input: String): Int = {
    val guardSleepRanges = parse(input)

    val (maxSleepGuardId, _) = guardSleepRanges.mapValues(_.map(r => r.end - r.start).sum).maxBy(_._2)
    val sleepRangesForMaxSleepGuard = guardSleepRanges(maxSleepGuardId)
    val timesSleptForMinute = (0 to 60)
      .map(minute => sleepRangesForMaxSleepGuard.count(r => r.start <= minute && minute < r.end))
    val mostCommonSleepMinute = timesSleptForMinute.indexWhere(t => t == timesSleptForMinute.max)
    maxSleepGuardId * mostCommonSleepMinute
  }

  def part2(input: String): Int = {
    val guardSleepRanges = parse(input)

    val ((gid, minute), _) = guardSleepRanges.keys.flatMap(guardId => {
      val sleepRanges = guardSleepRanges(guardId)
      (0 to 60)
        .map(minute => (guardId, minute) -> sleepRanges.count(r => r.start <= minute && minute < r.end))
    }).maxBy(_._2)

    gid * minute
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day04.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
