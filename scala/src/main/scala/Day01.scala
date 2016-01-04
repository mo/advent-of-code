object Day01 {

  def findFloor(input: String): Int = {
    input.map {
      case '(' => 1
      case ')' => -1
    }.sum
  }

  def findBasementIndex(input: String): Int = {
    input.map {
      case '(' => 1
      case ')' => -1
    }.scanLeft(0)(_+_).indexOf(-1)
  }

  def solve(): (Int, Int) = {
    val directions = DataFolder.openFile("day01.txt").mkString
    (findFloor(directions), findBasementIndex(directions))
  }

  def main(args: Array[String]): Unit = {
    val (finalFloor, firstBasementIndex) = solve()
    println(s"final floor: $finalFloor")
    println(s"first entered basement at index: $firstBasementIndex")
  }

}
