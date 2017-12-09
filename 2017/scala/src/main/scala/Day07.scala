

object Day07 {

  case class Program(name: String, weight: Int, parent: Option[String], childNames: List[String])

  def parseInput(input: String): List[Program] = {
    val programLine = """(\w+) \((\d+)\)(?: -> )?(.+)?""".r
    val programs = input.split("\n").toList
      .map {
        case programLine(name, weight, children) =>
          Program(name, weight.toInt, None, Option(children).map(_.split(", ").toList).getOrElse(List()))
      }
    programs.map(prog => prog.copy(parent = programs.find(_.childNames.contains(prog.name)).map(_.name)))
  }

  def part1(input: String): String = parseInput(input).find(_.parent.isEmpty).get.name

  def part2(input: String): Int = {
    val programs = parseInput(input)
    val root = programs.find(_.parent.isEmpty).get
    val progMap = programs.map(prog => prog.name -> prog).toMap

    def totalWeight(prog: Program): Int = prog.weight + prog.childNames.map(progMap).map(totalWeight).sum
    def findCorrectWeight(program: Program, correctTotalWeight: Int): Int = {
      val uniqueChildWeights = program.childNames
        .map(progMap)
        .map(prog => (prog, totalWeight(prog)))
        .groupBy { case (_, totalWeight) => totalWeight }
      if (uniqueChildWeights.size == 1) {
        val neededWeightAdjustment = correctTotalWeight - totalWeight(program)
        program.weight + neededWeightAdjustment
      } else {
        val unbalancedChild = uniqueChildWeights.toList.find { case (len, instances) => instances.lengthCompare(1) == 0 }.get._2.head._1
        val correctTotalWeight = uniqueChildWeights.toList.find { case (len, instances) => instances.lengthCompare(1) != 0 }.get._1
        findCorrectWeight(unbalancedChild, correctTotalWeight)
      }
    }

    findCorrectWeight(root, totalWeight(root))
  }

  def solve(): (String, Int) = {
    val input = DataFolder.openFile("day07.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
