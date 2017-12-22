object Day15 {

  val sixteenOnes: Int = (1 << 16) - 1
  def binaryStr(n: Long, len: Int): String = s"%${len}s".format(n.toBinaryString).replace(' ', '0')

  def generate(startValueA: Long, startValueB: Long, steps: Int, filterA: Long => Boolean, filterB: Long => Boolean): Iterator[(Long, Long)]= {
    val genA = Iterator.iterate(startValueA)(n => (n * 16807) % 2147483647).drop(1).filter(filterA)
    val genB = Iterator.iterate(startValueB)(n => (n * 48271) % 2147483647).drop(1).filter(filterB)
    genA.zip(genB).slice(0, steps)
  }

  def part1(startValueA: Long, startValueB: Long, steps: Int): Int = {
    generate(startValueA, startValueB, steps, _ => true, _ => true)
      .count { case(a, b) => (a & sixteenOnes) == (b & sixteenOnes) }
  }
  def part2(startValueA: Long, startValueB: Long, steps: Int): Int =
    generate(startValueA, startValueB, steps, _ % 4 == 0, _ % 8 == 0)
      .count { case(a, b) => (a & sixteenOnes) == (b & sixteenOnes) }

  def solve(): (Int, Int) = {
    (part1(722, 354, 40000000), part2(722, 354, 5000000))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
