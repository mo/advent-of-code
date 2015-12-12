object Day01 {
  def findFloor(input: String): Int = {
    var floor = 0
    for (ch <- input) {
      ch match {
        case '(' => floor += 1
        case ')' => floor -= 1
      }
    }
    floor
  }

  def findBasementIndex(input: String): Int = {
    var index = 0
    var floor = 0
    for (ch <- input) {
      index += 1
      ch match {
        case '(' => floor += 1
        case ')' => floor -= 1
      }
      if (floor == -1) {
        return index
      }
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val directions = io.Source.fromFile("data/day01.txt").mkString
    println("final floor: " + findFloor(directions))
    println("first entered basement at index: " + findBasementIndex(directions))
  }
}
