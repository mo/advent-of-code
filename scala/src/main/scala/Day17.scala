
object Day17 {

  def nextCoefficients(coefficients: List[Int]): List[Int] = {
    coefficients match {
      case Nil => List(1)
      case init :+ 0 => init ++ List(1)
      case init :+ 1 => nextCoefficients(init) ++ List(0)
    }
  }

  def dot(a: List[Int], b: List[Int]): Int = {
    require(a.length == b.length)
    (a zip b).map { case (c,d) => c*d }.sum
  }

  def calcWaysToStore(targetLiters: Int, containerSizes: List[Int]): List[List[Int]] = {
    val initialUseCoefficients = List.fill(containerSizes.length)(0)
    val finalUseCoefficients = List.fill(containerSizes.length)(1)
    Iterator.iterate(initialUseCoefficients)(nextCoefficients).takeWhile((coeffs) => coeffs != finalUseCoefficients).filter(
      (useCoeffs) => dot(useCoeffs, containerSizes) == targetLiters
    ).toList
  }

  def solve(): (Int, Int) = {
    val containerSizes = DataFolder.openFile("day17.txt").getLines().map(_.toInt).toList
    val waysToStore = calcWaysToStore(150, containerSizes)

    val part1 = waysToStore.length

    val containersNeed = waysToStore.map(_.sum)
    val part2 = containersNeed.count(_ == containersNeed.min)

    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
