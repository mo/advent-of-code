import scala.annotation.tailrec

object Day10 {

  def knotHash(length: Int, input: List[Int]): List[Int] = {

    @tailrec
    def impl(currentPos: Int, skipSize: Int, list: List[Int], remainingKnots: List[Int]): List[Int] = {
      if (remainingKnots.isEmpty) {
        list
      } else {
        val knotLength = remainingKnots.head
        val newPos = (currentPos + knotLength + skipSize) % list.length
        val listStartingAtCurrentPos = list.drop(currentPos) ++ list.take(currentPos)
        val listWithKnotApplied = listStartingAtCurrentPos.take(knotLength).reverse ++ listStartingAtCurrentPos.drop(knotLength)
        val newList = listWithKnotApplied.drop(list.length - currentPos) ++ listWithKnotApplied.take(list.length - currentPos)
        impl(newPos, skipSize + 1, newList, remainingKnots.tail)
      }
    }

    impl(0, 0, (0 until length).toList, input)
  }

  def knotHashFull(input: String): String = {
    val inputData = input.map(_.toInt) ++ List(17, 31, 73, 47, 23)
    val sparseHash = knotHash(256, (1 to 64).toList.flatMap(i => inputData))
    val denseHash = (0 to 15).map(i => sparseHash.slice(i * 16, (i + 1) * 16).foldLeft(0)(_^_))
    denseHash.map(n => ("0" + n.toHexString).takeRight(2)).mkString
  }

  def part1(hashLength: Int, input: String): Int = knotHash(hashLength, input.split(",").toList.map(_.toInt)).slice(0, 2).product
  def part2(input: String): String = knotHashFull(input)

  def solve(): (Int, String) = {
    val input = DataFolder.openFile("day10.txt").mkString
    (part1(256, input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
