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
    sideAreas.map(_*2).sum + slack
  }

  def calcNeededRibbon(giftDimensions: (Int, Int, Int)): Int = {
    val (l, w, h) = giftDimensions
    Seq(l, w, h).sorted.take(2).map(_*2).sum + l*w*h
  }

  def solve(): (Int, Int) = {
    val inputFile = DataFolder.openFile("day02.txt")
    var totalPaperNeeded = 0
    var totalRibbonNeeded = 0
    for (giftDimensionLine <- inputFile.getLines()) {
      val giftDimensions = parseDimensions(giftDimensionLine)
      totalPaperNeeded += calcNeededPaper(giftDimensions)
      totalRibbonNeeded += calcNeededRibbon(giftDimensions)
    }
    (totalPaperNeeded, totalRibbonNeeded)
  }

  def main(args: Array[String]): Unit = {
    val (totalPaperNeeded, totalRibbonNeeded) = solve()
    println("total paper needed: " + totalPaperNeeded + " square feet")
    println("total ribbon needed: " + totalRibbonNeeded + " feet")
  }

}
