
object Day16 {

  case class Sue(number: Int, properties: Map[String, Int])

  val targetProperties = List(
    ("children", 3),
    ("cats", 7),
    ("samoyeds", 2),
    ("pomeranians", 3),
    ("akitas", 0),
    ("vizslas", 0),
    ("goldfish", 5),
    ("trees", 3),
    ("cars", 2),
    ("perfumes", 1)
  )

  def parseSueSpec(specStr: String): Sue = {
    val number = """Sue (\d+)""".r.findFirstMatchIn(specStr).get.group(1).toInt
    val characteristics = """(\w+): (\d+)""".r.findAllMatchIn(specStr).map(m => m.group(1) -> m.group(2).toInt).toMap
    Sue(number, characteristics)
  }

  def solve(): (Int, Int) = {
    val allCandidateSues = DataFolder.openFile("day16.txt").getLines().map(parseSueSpec).toList

    var candidatesPart1 = allCandidateSues
    targetProperties.foreach { t =>
      candidatesPart1 = candidatesPart1.filter(sue => !sue.properties.contains(t._1) || sue.properties(t._1) == t._2)
    }
    assume(candidatesPart1.length == 1)
    val part1 = candidatesPart1.head.number

    var candidatesPart2 = allCandidateSues
    targetProperties.foreach { t =>
      val propertyName = t._1
      val propertyValue = t._2
      propertyName match {
        case "cats" | "trees" => candidatesPart2 = candidatesPart2.filter(sue => !sue.properties.contains(propertyName) || sue.properties(propertyName) > propertyValue)
        case "pomeranians" | "goldfish" => candidatesPart2 = candidatesPart2.filter(sue => !sue.properties.contains(propertyName) || sue.properties(propertyName) < propertyValue)
        case _ => candidatesPart2 = candidatesPart2.filter(sue => !sue.properties.contains(propertyName) || sue.properties(propertyName) == propertyValue)
      }
    }
    assume(candidatesPart2.length == 1)
    val part2 = candidatesPart2.head.number

    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part1 == " + part2)
  }

}
