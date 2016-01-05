object Day03 {

  def calcVisitedHouses(directions: String): Set[(Int, Int)] = {
    directions.map {
      case '^' => (0, 1)
      case 'v' => (0, -1)
      case '<' => (-1, 0)
      case '>' => (1, 0)
    }.scanLeft((0,0)) { case ((a,b),(c,d)) => (a+c, b+d) }.distinct.toSet
  }

  def calcVisitedHousesPart2(directions: String): Set[(Int, Int)] = {
    val santaDirections = directions.sliding(1, 2).mkString
    val roboSantaDirections = directions.tail.sliding(1, 2).mkString
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
    println(s"houses visited part 1: $housesVisitedPart1")
    println(s"houses visited part 2: $housesVisitedPart2")
  }

}
