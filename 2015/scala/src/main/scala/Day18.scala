object Day18 {

  def loadLights(lightStr: String): Array[Array[Boolean]] = {
    val allLines = lightStr.split("\n").map(_.trim).filter(_ != "")
    val lights = Array.ofDim[Boolean](allLines(0).length, allLines.length)
    for ((line, y) <- allLines.zipWithIndex) {
      for ((ch, x) <- line.view.zipWithIndex) {
        ch match {
          case '#' => lights(x)(y) = true
          case '.' => lights(x)(y) = false
        }
      }
    }
    lights
  }

  def saveLights(lights: Array[Array[Boolean]]): String = {
    var lightStr = ""
    for (y <- lights.indices) {
      for (x <- lights(0).indices) {
        lightStr += (if (lights(x)(y)) "#" else ".")
      }
      lightStr += "\n"
    }
    lightStr.trim()
  }

  def getLightState(x: Int, y: Int, lights: Array[Array[Boolean]]): Boolean = {
    if (y < 0 || y >= lights.length) {
      return false
    }
    if (x < 0 || x >= lights(0).length) {
      return false
    }
    lights(x)(y)
  }

  def animateLights(lights: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    val newLights = Array.ofDim[Boolean](lights(0).length, lights.length)
    for (y <- lights.indices) {
      for (x <- lights(0).indices) {
        val neighborLightsOn = List(
          getLightState(x - 1, y - 1, lights),
          getLightState(x,     y - 1, lights),
          getLightState(x + 1, y - 1, lights),
          getLightState(x - 1, y,     lights),
          getLightState(x + 1, y,     lights),
          getLightState(x - 1, y + 1, lights),
          getLightState(x,     y + 1, lights),
          getLightState(x + 1, y + 1, lights)
        ).count(_ == true)
        if (lights(x)(y)) {
          newLights(x)(y) = if (neighborLightsOn == 2 || neighborLightsOn == 3) true else false
        } else {
          newLights(x)(y) = if (neighborLightsOn == 3) true else false

        }
      }
    }
    newLights
  }

  def animateLights(oldLights: Array[Array[Boolean]], steps: Int): Array[Array[Boolean]] = {
    var lights = oldLights
    1 to steps foreach { _ =>
      lights = animateLights(lights)
    }
    lights
  }

  def autoLightCorners(oldLights: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    val newLights = oldLights.clone()
    val xLength = oldLights(0).length
    val yLength = oldLights.length
    newLights(0)(0) = true
    newLights(xLength-1)(0) = true
    newLights(0)(yLength-1) = true
    newLights(xLength-1)(yLength-1) = true
    newLights
  }

  def animateLightsPart2(oldLights: Array[Array[Boolean]], steps: Int): Array[Array[Boolean]] = {
    var newLights = autoLightCorners(oldLights.clone())
    1 to steps foreach { _ =>
      newLights = autoLightCorners(animateLights(newLights))
    }
    newLights
  }

  def solve(): (Int, Int) = {
    var lights = loadLights(DataFolder.openFile("day18.txt").mkString)
    lights = animateLights(lights, 100)
    val part1 = lights.map(_.count(_ == true)).sum

    lights = loadLights(DataFolder.openFile("day18.txt").mkString)
    lights = animateLightsPart2(lights, 100)
    val part2 = lights.map(_.count(_ == true)).sum

    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
