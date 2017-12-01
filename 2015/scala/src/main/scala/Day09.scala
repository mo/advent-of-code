object Day09 {

  def calcShortestLongestPath(distancesSpec: String): (Int, Int) = {
    val distanceSpecPattern = """(\w+) to (\w+) = (\d+)""".r
    val distanceSpecLines = distancesSpec.split("\n").map(_.trim)
    val locations = distanceSpecLines.map {
      case distanceSpecPattern(from, to, distance) => List(from, to)
    }.reduce(_++_).distinct
    val distanceMap = distanceSpecLines.map {
      case distanceSpecPattern(from, to, distance) => List(((from, to), distance.toInt), ((to, from), distance.toInt))
    }.reduce(_++_).toMap
    def routeDistance(locationList: List[String]): Int = {
      locationList.sliding(2).map {
        case Seq(from, to) => distanceMap((from, to))
      }.sum
    }
    val allPathLengths = locations.permutations.toList.map(routeDistance)
    val shortestPath = allPathLengths.min
    val longestPath = allPathLengths.max
    (shortestPath, longestPath)
  }

  def solve(): (Int, Int) = {
    val distancesSpec = DataFolder.openFile("day09.txt").mkString
    calcShortestLongestPath(distancesSpec)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
