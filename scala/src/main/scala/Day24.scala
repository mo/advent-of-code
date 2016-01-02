object Day24 {

  def fixedLengthRestrictedCompositions(number: Long, termCount: Long, allowedTerms: List[Long]): List[List[Long]] = {
    require(number >= 0)
    require(termCount >= 1)
    require(allowedTerms.forall(_ >= 0))
    if (termCount == 1) {
      allowedTerms.filter(_ == number).map(_ => List(number))
    } else {
      allowedTerms.filter(_ <= number).zipWithIndex.flatMap { case (term, idx) =>
        val allowedTermsMinusTerm = allowedTerms.slice(0, idx) ++ allowedTerms.slice(idx + 1, allowedTerms.length)
        fixedLengthRestrictedCompositions(number - term, termCount - 1, allowedTermsMinusTerm).map(resComp => List(term) ++ resComp)
      }
    }
  }

  def calcIdealConfigQE(weights: List[Long], compartmentCount: Long): Long = {
    val weightSum = weights.sum
    require(weightSum % compartmentCount == 0)
    val groupSize = weightSum / compartmentCount

    Iterator.from(1).foreach { passengerCompartmentPackageCount =>
      val configs = fixedLengthRestrictedCompositions(groupSize, passengerCompartmentPackageCount, weights.sortBy(-1*_))
      if (configs.nonEmpty) {
        return configs.sortBy(_.product).head.product
      }
    }
    -1
  }

  def solve(): (Long, Long) = {
    val weights = DataFolder.openFile("day24.txt").getLines().map(_.toLong).toList
    val part1 = calcIdealConfigQE(weights, 3)
    val part2 = calcIdealConfigQE(weights, 4)
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
