object Day17 {

  def spinlock(steps: Int, inserts: Int): (Int, List[Int])= {
    var buffer = List(0)
    var currentPos = 0
    (1 to inserts).foreach(n => {
      currentPos = (currentPos + steps) % buffer.length
      buffer = buffer.patch(currentPos + 1, List(n), 0)
      currentPos += 1
    })
    (currentPos, buffer)
  }

  def part1(steps: Int): Int = {
    val (finalPos, buffer) = spinlock(steps, 2017)
    buffer(finalPos + 1)
  }
  def part2(steps: Int): Int = {
    var currentPos = 0
    var answer = 0
    (1 to 50000000).foreach(n => {
      currentPos = (currentPos + steps) % n
      if (currentPos == 0) {
        answer = n
      }
      currentPos += 1
    })
    answer
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day17.txt").mkString.toInt
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
