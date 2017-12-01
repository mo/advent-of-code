object Day13 {

  val gainPattern = """(\w+) would gain (\d+) happiness units by sitting next to (\w+).""".r
  val lossPattern = """(\w+) would lose (\d+) happiness units by sitting next to (\w+).""".r

  def optimalSeatingHappiness(seatingPreferences: String, apatheticGuests: List[String]): Int = {
    val seatingPreferenceLines = seatingPreferences.split("\n").map(_.trim)
    val allPersons = seatingPreferenceLines.map(_.split(" ")(0)).distinct ++ apatheticGuests
    val individualHappiness = seatingPreferenceLines.map {
      case gainPattern(personA, happiness, personB) => (personA, personB) -> happiness.toInt
      case lossPattern(personA, happiness, personB) => (personA, personB) -> -happiness.toInt
    }.toMap
    def seatingHappiness(seating: List[String]): Int = {
      val reverseSeating = seating.reverse
      seating.sliding(2).map {
        case List(personA, personB) => individualHappiness.getOrElse((personA, personB), 0)
      }.sum + individualHappiness.getOrElse((seating.last, seating.head), 0) +
      reverseSeating.sliding(2).map {
        case List(personA, personB) => individualHappiness.getOrElse((personA, personB), 0)
      }.sum + individualHappiness.getOrElse((reverseSeating.last, reverseSeating.head), 0)
    }
    val optionalHappiness = allPersons.permutations.map(perm => seatingHappiness(perm.toList)).max
    optionalHappiness
  }

  def solve(): (Int, Int) = {
    val seatingPreferences = DataFolder.openFile("day13.txt").mkString
    (optimalSeatingHappiness(seatingPreferences, List()), optimalSeatingHappiness(seatingPreferences, List("molsson")))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
