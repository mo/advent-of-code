object Day20 {

  def calcDivisors(number: Int): List[Int] = {
    if (number == 1) {
      return List(1)
    }
    List(1) ++ (2 to math.sqrt(number).toInt).filter(number % _ == 0).flatMap(
      d => {
        val other = number / d
        if (d != other) {
          List(d, other)
        } else {
          List(d)
        }
      }
    ).toList ++ List(number)
  }

  def calcGiftsDeliveredPart1(houseNumber: Int): Int = {
    10 * calcDivisors(houseNumber).sum
  }

  def calcHouseThatGetsAtleastNPresentsPart1(n: Int): Int = {
    Stream.from(1).find(calcGiftsDeliveredPart1(_) >= n).get
  }

  def calcGiftsDeliveredPart2(houseNumber: Int): Int = {
    11 * calcDivisors(houseNumber).filter(houseNumber <= 50*_).sum
  }

  def calcHouseThatGetsAtleastNPresentsPart2(n: Int): Int = {
    Stream.from(1).find(calcGiftsDeliveredPart2(_) >= n).get
  }

  def solve(): (Int, Int) = {
    val part1 = calcHouseThatGetsAtleastNPresentsPart1(36000000)
    val part2 = calcHouseThatGetsAtleastNPresentsPart2(36000000)
    (part1, part2)
  }

  def main(args: Array[String]) = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
