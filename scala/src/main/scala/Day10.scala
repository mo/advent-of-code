object Day10 {

  def lookAndSay(str: String): String = { """([[0-9]])\1*""".r.findAllIn(str).map(run => run.length.toString + run(0)).mkString }

  def solve(): (Int, Int) = {
    var number = "1321131112"
    (1 to 40).foreach { i =>
      number = lookAndSay(number)
    }
    val part1 = number.length
    (1 to 10).foreach { i =>
      number = lookAndSay(number)
    }
    val part2 = number.length
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
