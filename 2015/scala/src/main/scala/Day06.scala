import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Day06 {

  def parseRange(rangeStr: String) : IndexedSeq[(Int, Int)] = {
    val RangeSpecs = """(\d+),(\d+) through (\d+),(\d+)""".r
    val RangeSpecs(x1, y1, x2, y2) = rangeStr
    for (x <- x1.toInt to x2.toInt; y <- y1.toInt to y2.toInt) yield (x, y)
  }

  def parseLine(line: String) : (String, IndexedSeq[(Int, Int)]) = {
    if (line.startsWith("turn on ")) {
      val action = "on"
      val rangeStr = line.slice("turn on ".length, line.length)
      return (action, parseRange(rangeStr))
    } else if (line.startsWith("turn off ")) {
      val action = "off"
      val rangeStr = line.slice("turn off ".length, line.length)
      return (action, parseRange(rangeStr))
    } else if (line.startsWith("toggle ")) {
      val action = "toggle"
      val rangeStr = line.slice("toggle ".length, line.length)
      return (action, parseRange(rangeStr))
    }
    throw new IllegalArgumentException("invalid line: " + line)
  }

  def savePart1Image(lights: Array[Array[Boolean]]): Unit = {
    val img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB)
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        if (lights(x)(y)) {
          img.setRGB(x, y, Color.WHITE.getRGB)
        } else {
          img.setRGB(x, y, Color.BLACK.getRGB)
        }
      }
    }
    ImageIO.write(img, "png", new File("day06-part1.png"))
  }

  def solvePart1() : Int = {
    val lights = Array.ofDim[Boolean](1000, 1000)
    for (line <- DataFolder.openFile("day06.txt").getLines()) {
      val (action, targetLights) = parseLine(line)
      if (action == "on") {
        for ((x, y) <- targetLights) {
          lights(x)(y) = true
        }
      } else if (action == "off") {
        for ((x, y) <- targetLights) {
          lights(x)(y) = false
        }
      } else if (action == "toggle") {
        for ((x, y) <- targetLights) {
          lights(x)(y) = !lights(x)(y)
        }
      }
    }
    var numLightsOn = 0
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        if (lights(x)(y)) {
          numLightsOn += 1
        }
      }
    }

    savePart1Image(lights)

    numLightsOn
  }

  def savePart2Image(lights: Array[Array[Int]]): Unit = {
    var brightestLight = 0
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        if (lights(x)(y) > brightestLight) {
          brightestLight = lights(x)(y)
        }
      }
    }
    val img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB)
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        img.setRGB(x, y, (255*lights(x)(y))/brightestLight)
      }
    }
    ImageIO.write(img, "png", new File("day06-part2.png"))
  }

  def solvePart2(): Int = {
    val lights = Array.ofDim[Int](1000, 1000)
    for (line <- DataFolder.openFile("day06.txt").getLines()) {
      val (action, targetLights) = parseLine(line)
      if (action == "on") {
        for ((x, y) <- targetLights) {
          lights(x)(y) += 1
        }
      } else if (action == "off") {
        for ((x, y) <- targetLights) {
          if (lights(x)(y) > 0) {
            lights(x)(y) -= 1
          }
        }
      } else if (action == "toggle") {
        for ((x, y) <- targetLights) {
          lights(x)(y) += 2
        }
      }
    }

    var totalBrightness = 0
    for (x <- 0 to 999) {
      for (y <- 0 to 999) {
        totalBrightness += lights(x)(y)
      }
    }

    savePart2Image(lights)

    totalBrightness
  }

  def solve() : (Int, Int) = {
    (solvePart1(), solvePart2())
  }

  def main(args: Array[String]): Unit = {
    val (numLightsOn, totalBrightness) = solve()
    println("number of lights on: " + numLightsOn)
    println("total brightness on: " + totalBrightness)
  }

}
