object Day16 {

  def parse(input: String): List[List[Char]] = input.split(",").map(_.toList).toList

  def dance(programs: String, moves: List[List[Char]]): String = {
    moves.foldLeft(programs)((progs, move) => move match {
      case 's' :: rest =>
        val rotate = progs.length - rest.mkString.toInt
        progs.drop(rotate) + progs.take(rotate)
      case 'x' :: rest =>
        rest.mkString.split("/").map(_.toInt) match {
          case Array(i, j) =>
            progs.updated(i, progs(j)).updated(j, progs(i))
        }
      case 'p' :: rest =>
        rest.mkString.split("/") match {
          case Array(a, b) =>
            val (i, j) = (progs.indexOf(a), progs.indexOf(b))
            progs.updated(i, progs(j)).updated(j, progs(i))
        }
    })
  }

  def part1(input: String): String = dance(('a' to 'p').mkString, parse(input))
  def part2(input: String): String = {
    val initialOrder = ('a' to 'p').mkString
    val moves = parse(input)
    val programOrders = Iterator.iterate(initialOrder)(progs => dance(progs, moves))
    Utils.indexesOfFirstDuplicate(programOrders.toTraversable) match {
      case Some(List(i, j)) =>
        val neededDances = i + ((1000000000 - i) % (j - i))
        Iterator.iterate(initialOrder)(progs => dance(progs, moves)).drop(neededDances).next
    }
  }

  def solve(): (String, String) = {
    val input = DataFolder.openFile("day16.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
