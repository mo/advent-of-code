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

  def solve(): (Int, Int) = {
    val directions = io.Source.fromFile("data/day01.txt").mkString
    val finalFloor = findFloor(directions)
    val firstBasementIndex = findBasementIndex(directions)
    (finalFloor, firstBasementIndex)
  }

  def main(args: Array[String]): Unit = {
    val (finalFloor, firstBasementIndex) = solve()
    println("final floor: " + finalFloor)
    println("first entered basement at index: " + firstBasementIndex)
  }
}
