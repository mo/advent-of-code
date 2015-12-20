object Day14 {

  case class Reindeer(name: String, speed: Int, stamina: Int, rest: Int)

  val REINDEER_SPEC = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".r

  def parseReindeer(reindeerSpec: String): Reindeer = {
    reindeerSpec match {
      case REINDEER_SPEC(name, speed, stamina, rest) => Reindeer(name, speed.toInt, stamina.toInt, rest.toInt)
    }
  }

  def distanceTravelled(reindeer: Reindeer, travelTimeSeconds: Int): Int = {
    val fullCycles = travelTimeSeconds / (reindeer.stamina + reindeer.rest)
    val remainingSeconds = travelTimeSeconds % (reindeer.stamina + reindeer.rest)
    val cycleDistance = reindeer.speed * reindeer.stamina
    val partialCycleDistance = reindeer.speed * List(remainingSeconds, reindeer.stamina).min
    fullCycles * cycleDistance + partialCycleDistance
  }


  def racePart1(allReindeer: List[Reindeer], travelTimeSeconds: Int): List[(Reindeer, Int)] = {
    allReindeer.map(deer => (deer, distanceTravelled(deer, travelTimeSeconds))).sortBy(_._2).reverse
  }

  def racePart2(allReindeer: List[Reindeer], travelTimeSeconds: Int): List[(String, Int)] = {
    var totalPoints: Map[String, Int] = allReindeer.map(reindeer => (reindeer.name, 0)).toMap
    (1 to travelTimeSeconds).foreach { roundNum =>
      val roundResults = racePart1(allReindeer, roundNum)
      val roundWinnerDistance = roundResults.map(_._2).max
      for ((reindeer, distance) <- roundResults) {
        if (distance == roundWinnerDistance) {
          totalPoints = totalPoints updated (reindeer.name, totalPoints.getOrElse(reindeer.name, 0) + 1)
        }
      }
    }
    totalPoints.toList.sortBy(_._2).reverse
  }

  def solve(): (Int, Int) = {
    val allReindeer = DataFolder.openFile("day14.txt").getLines().map(parseReindeer).toList
    (racePart1(allReindeer, 2503).head._2, racePart2(allReindeer, 2503).head._2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
