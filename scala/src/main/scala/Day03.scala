
object Day03 {

  def calcVisitedHouses(directions: String): Set[(Int, Int)] = {
    var currentPosition = (0,0)
    var visitedHouses = Set(currentPosition)
    for (ch <- directions) {
      ch match {
        case '^' => currentPosition = (currentPosition._1, currentPosition._2 - 1)
        case 'v' => currentPosition = (currentPosition._1, currentPosition._2 + 1)
        case '<' => currentPosition = (currentPosition._1 - 1, currentPosition._2)
        case '>' => currentPosition = (currentPosition._1 + 1, currentPosition._2)
      }
      visitedHouses += currentPosition
    }
    visitedHouses
  }

  def calcVisitedHousesPart2(directions: String): Set[(Int, Int)] = {
    val santaDirections = directions.zipWithIndex.filter(_._2 % 2 == 0).map(_._1).mkString
    val roboSantaDirections = directions.zipWithIndex.filter(_._2 % 2 == 1).map(_._1).mkString
    calcVisitedHouses(santaDirections) ++ calcVisitedHouses(roboSantaDirections)
  }

  def solve(): (Int, Int) = {
    val directions = DataFolder.openFile("day03.txt").mkString
    val housesVisitedPart1 = calcVisitedHouses(directions).size
    val housesVisitedPart2 = calcVisitedHousesPart2(directions).size
    (housesVisitedPart1, housesVisitedPart2)
  }

  def main(args: Array[String]): Unit = {
    val (housesVisitedPart1, housesVisitedPart2) = solve()
    println("houses visited part 1: " + housesVisitedPart1)
    println("houses visited part 2: " + housesVisitedPart2)
  }

}
