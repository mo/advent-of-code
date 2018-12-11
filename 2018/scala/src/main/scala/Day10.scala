import scala.annotation.tailrec

object Day10 {

  case class Star(x: Int, y: Int, dx: Int, dy: Int)

  case class CanvasSize(width: Int, height: Int)
  def canvasSize(stars: List[Star]): CanvasSize = {
    val xMin = stars.map(s => s.x).min
    val xMax = stars.map(s => s.x).max
    val yMin = stars.map(s => s.y).min
    val yMax = stars.map(s => s.y).max
    CanvasSize(xMax - xMin, yMax - yMin)
  }

  def renderStars(stars: List[Star]): String = {
    val xMin = stars.map(s => s.x).min
    val xMax = stars.map(s => s.x).max
    val yMin = stars.map(s => s.y).min
    val yMax = stars.map(s => s.y).max
    val output = StringBuilder.newBuilder
    val pixels = stars.map(s => (s.x, s.y))
    (yMin to yMax) foreach (y => {
      (xMin to xMax) foreach (x => {
        if (pixels.contains((x, y))) { output.append("#") } else { output.append(".") }
      })
      output.append("\n")
    })
    output.toString
  }

  def simulateStars(totalSeconds: Int, input: String): String = {
    val line = """position=<(-?\d+),(-?\d+)>velocity=<(-?\d+),(-?\d+)>""".r
    val initialStars = input.replaceAll(" ", "").split("\n").map {
      case line(x, y, dx, dy) => Star(x.toInt, y.toInt, dx.toInt, dy.toInt)
    }.toList

    @tailrec
    def impl(currentSecond: Int, stars: List[Star]): String = {
      val size = canvasSize(stars)
      if (size.width < 200 && size.height < 200) {
        println(s"After $currentSecond seconds:")
        println(renderStars(stars) + "\n\n")
      }
      if (currentSecond == totalSeconds) {
        renderStars(stars)
      } else {
        impl(currentSecond + 1, stars.map(s => Star(s.x + s.dx, s.y + s.dy, s.dx, s.dy)))
      }
    }

    impl(0, initialStars)
  }

  def main(args: Array[String]): Unit = {
    val input = DataFolder.openFile("day10.txt").mkString
    simulateStars(200000, input)
  }
}
