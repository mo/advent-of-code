

object Day06 {
  case class Point(x: Int, y: Int)

  def parse(input: String): Seq[Point] = {
    val coordLine = """(\d+), (\d+)""".r
    input.split("\n").map {
      case coordLine(x, y) => Point(x.toInt, y.toInt)
    }
  }

  def rectCoords(topLeft: Point, bottomRight: Point): Seq[Point] =
    for {
      y <- topLeft.y to bottomRight.y
      x <- topLeft.x to bottomRight.x
    } yield Point(x, y)

  def bbox(points: Seq[Point]): (Int, Int, Int, Int) = {
    (points.map(_.x).min, points.map(_.x).max, points.map(_.y).min, points.map(_.y).max)
  }

  def manhattanDistance(a: Point, b: Point): Int = Math.abs(a.x - b.x) + Math.abs(a.y - b.y)

  def pointAreasWithinBBox(coords: Seq[Point], bboxTopLeft: Point, bboxBottomRight: Point): Map[Point, Int] =
    rectCoords(bboxTopLeft, bboxBottomRight)
      .flatMap(p => {
        val distanceToClosest = coords.map(manhattanDistance(p, _)).min
        val closestPoints = coords.filter(manhattanDistance(p, _) == distanceToClosest)
        if (closestPoints.length == 1) {
          Some(closestPoints.head)
        } else {
          None
        }
      })
      .groupBy(identity)
      .mapValues(_.length)

  def part1(input: String): Int = {
    val points = parse(input)
    val (xMin, xMax, yMin, yMax) = bbox(points)
    val areasInBBox = pointAreasWithinBBox(points, Point(xMin, yMin), Point(xMax, yMax))
    val areasInBiggerBox = pointAreasWithinBBox(points, Point(xMin - 1, yMin - 1), Point(xMax + 1, yMax + 1))

    points
      .filter(p => areasInBBox(p) == areasInBiggerBox(p))
      .map(p => areasInBBox(p))
      .max
  }

  def part2(maxTotalDistance: Int, input: String): Int = {
    val points = parse(input)
    val (xMin, xMax, yMin, yMax) = bbox(points)
    rectCoords(Point(xMin, yMin), Point(xMax, yMax))
      .count(pt => points.map(p => manhattanDistance(p, pt)).sum < maxTotalDistance)
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day06.txt").mkString
    (part1(input), part2(10000, input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
