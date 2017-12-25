object Day19 {

  def traceTubes(input: String): (String, Int) = {
    val grid = input.split("\n").map(_.padTo(205, ' '))
    var pos = (grid.head.indexOf("|"), 0)
    var delta = (0, 1)
    var foundLetters = ""
    var stepsTaken = 0
    var done = false
    while (!done) {
      grid(pos._2)(pos._1) match {
        case ch if ch.isLetter =>
          foundLetters += ch
        case '+' =>
          delta match {
            case (0, _) =>
              if (grid(pos._2)(pos._1 - 1) != ' ') {
                delta = (-1, 0)
              } else {
                delta = (1, 0)
              }
            case (_, 0) =>
              if (grid(pos._2 - 1)(pos._1) != ' ') {
                delta = (0, -1)
              } else {
                delta = (0, 1)
              }
          }
        case ' ' =>
          done = true
        case '|' | '-' | '*' =>
      }

//      grid(pos._2) = grid(pos._2).patch(pos._1, List('*'), 1)
//      grid.foreach(println)
//      println("\n\n")

      pos = (pos._1 + delta._1, pos._2 + delta._2)
      stepsTaken += 1
    }
    (foundLetters, stepsTaken - 1)
  }

  def part1(input: String): String = traceTubes(input)._1
  def part2(input: String): Int = traceTubes(input)._2

  def solve(): (String, Int) = {
    val input = DataFolder.openFile("day19.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
