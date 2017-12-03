import scala.collection.mutable

object Day03 {

  def layerSize(layerIdx: Int): Int = 2 * (layerIdx + 1) - 1

  def spiral: Iterator[(Int, Int)] =
    Iterator.from(1)
      .flatMap(layerIdx => {
        val size = layerSize(layerIdx)
        List((1, 0)) ++ List.fill(size - 2)((0, 1)) ++ List.fill(size - 1)((-1, 0)) ++ List.fill(size - 1)((0, -1)) ++ List.fill(size - 1)((1, 0))
      })
      .scanLeft((0, 0)) { case ((a, b), (c, d)) => (a + c, b + d)}

  def part1(squareNum: Int): Int = {
    spiral
      .drop(squareNum - 1)
      .map { case (x, y) => x.abs + y.abs }
      .next
  }

  def part2(limit: Int): Int = {
    val squares = mutable.Map[(Int, Int), Int]().withDefaultValue(0)
    squares((0, 0)) = 1

    spiral
      .drop(1)
      .map { case (x, y) =>
        val squareValue = squares((x - 1, y - 1)) + squares((x, y - 1)) + squares((x + 1, y - 1)) +
        squares((x - 1, y)) + squares((x + 1, y)) + squares((x - 1, y + 1)) + squares((x, y + 1)) + squares((x + 1, y + 1))
        squares((x, y)) = squareValue
        squareValue
      }.dropWhile(_ <= limit).next
  }

  def solve(): (Int, Int) = {
    (part1(368078), part2(368078))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
