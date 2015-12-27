import scala.util.Random

object Day19 {

  val TRANSFORMATION_SPEC = """(\w+) => (\w+)""".r

  def distinctOutputs(input: String, transformationsStr: String): Set[String] = {
    val transformations = transformationsStr.split("\n").map {
      case TRANSFORMATION_SPEC(from, to) => (from, to)
    }
    transformations.flatMap {
      case (from, to) => from.r.findAllMatchIn(input).map(_.start).map {
        startIdx => {
          input.slice(0, startIdx) + to + input.slice(startIdx + from.length, input.length)
        }
      }
    }.toSet
  }

  def stepsNeededToRestoreE(transformationsStr: String, startingMolecule: String): Int = {
    var transformations = transformationsStr.split("\n").map {
      case TRANSFORMATION_SPEC(from, to) => (from, to)
    }.toList
    def calcStepsNeeded(transformations: List[(String, String)], startingMolecule: String): Int = {
      var stepsUsed = 0
      var molecule = startingMolecule
      while (molecule != "e") {
        transformations.find { case (from, to) => molecule.contains(to) } match {
          case Some((from, to)) =>
            molecule = molecule.replaceFirst(to, from)
            stepsUsed += 1
          case None =>
            return -1
        }
      }
      stepsUsed
    }
    // Not exactly beautiful, I spent some time on a greedy DFS solution too but you still wouldn't get a solid
    // general solution and the solutions that use specific properties of the data are also non-ideal imho.
    // The most promising solutions seems to be approaching it as "parsing a context-free grammar" and using
    // well established algorithms, but this would require some reading before I could implement it.
    // The hack below at least delivers the correct answer for my data, and it passes the additional semi-tricky
    // crafted testcases that I wrote.
    (1 to 1000).map { _ =>
      transformations = Random.shuffle(transformations)
      calcStepsNeeded(transformations, startingMolecule)
    }.toList.filter(_ != -1).min
  }

  def solve() : (Int, Int) = {
    val inputLines = DataFolder.openFile("day19.txt").getLines().toList
    val transformationsStr = inputLines.filter(_.contains("=>")).mkString("\n")
    val medicineMolecule = inputLines.filter(line => !line.contains("=>") && line != "").mkString

    val part1 = distinctOutputs(medicineMolecule, transformationsStr).size

    val startTime = System.nanoTime()
    val part2 = stepsNeededToRestoreE(transformationsStr, medicineMolecule)
    println((System.nanoTime() - startTime)/1000000 + "ms")

    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
