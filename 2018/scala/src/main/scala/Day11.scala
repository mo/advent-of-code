import scala.collection.mutable

object Day11 {

  def power(x: Int, y: Int, gridSerial: Int): Int = {
    val rackId = x + 10
    val temp = rackId * (rackId * y + gridSerial)
    ((temp / 100) % 10) - 5
  }

  def totalPower(x: Int, y: Int, cells: Array[Array[Int]], squareSize: Int): Int =
    (0 until squareSize).map(dy => {
      (0 until squareSize).map(dx => {
        cells(x + dx)(y + dy)
      }).sum
    }).sum

  def largestPower(gridSerial: Int, squareSize: Int) : (Int, Int, Int) = {
    val cells = Array.ofDim[Int](300, 300)
    (0 until 300) foreach (y => {
      (0 until 300) foreach (x => {
        cells(x)(y) = power(x, y, gridSerial)
      })
    })
    (0 until 300 - squareSize + 1).toList.flatMap(y => {
      (0 until 300 - squareSize + 1).toList.map(x => {
        (x, y, totalPower(x, y, cells, squareSize))
      })
    }).maxBy(t => t._3)
  }

  def largestPower(gridSerial: Int) : (Int, Int, Int, Int) = {
    val buff = mutable.ListBuffer[(Int, Int, Int, Int)]()
    val (x, y, maxPower, squareSize) = (1 to 300).map(squareSize => {
      val (x, y, maxPower) = largestPower(gridSerial, squareSize)
      buff.append((x, y, maxPower, squareSize))
      println(s"$squareSize max so far =" + buff.maxBy(t => t._3))
      (x, y, maxPower, squareSize)
    }).maxBy(t => t._3)
    (x, y, maxPower, squareSize)
  }

  def part1(input: String): String = {
    val (x, y, _) = largestPower(input.toInt, 3)
    s"$x,$y"
  }

  def part2(input: String): String = {
    val (x, y, _, squareSize) = largestPower(input.toInt)
    s"$x,$y,$squareSize"
  }

  def solve(): (String, String) = {
    val input = DataFolder.openFile("day11.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }
}
