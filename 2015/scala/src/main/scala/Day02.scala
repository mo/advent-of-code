object Day02 {

  def parseDimensions(giftDimensionsStr: String) : (Int, Int, Int) = {
    giftDimensionsStr.split("x").map(_.toInt) match {
      case Array(l, w, h) => (l, w, h)
    }
  }

  def calcNeededPaper(giftDimensions: (Int, Int, Int)): Int = {
    val (l, w, h) = giftDimensions
    val sideAreas = Seq(l*w, w*h, h*l)
    val slack = sideAreas.min
    2 * sideAreas.sum + slack
  }

  def calcNeededRibbon(giftDimensions: (Int, Int, Int)): Int = {
    val (l, w, h) = giftDimensions
    2 * Seq(l, w, h).sorted.take(2).sum + l*w*h
  }

  def solve(): (Int, Int) = {
    val packageDimensions = DataFolder.openFile("day02.txt").getLines().map(parseDimensions).toList
    (packageDimensions.map(calcNeededPaper).sum, packageDimensions.map(calcNeededRibbon).sum)
  }

  def main(args: Array[String]): Unit = {
    val (totalPaperNeeded, totalRibbonNeeded) = solve()
    println(s"total paper needed: $totalPaperNeeded square feet")
    println(s"total ribbon needed: $totalRibbonNeeded feet")
  }

}
