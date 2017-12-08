import scala.collection.mutable

object Day06 {

//  def part1(input: List[Int]): Int = {
//    var banks = input.toArray
//    val stepsTo = mutable.HashMap[List[Int], Int]().withDefaultValue(0)
//    var reallocationCount = 0
//    while (stepsTo(banks.toList) != 0) {
//      stepsTo(banks.toList) = reallocationCount
//      val indexToReallocated = banks.indexWhere(_ == banks.max)
//      val blocks = banks(indexToReallocated)
//      banks(indexToReallocated) = 0
//      (1 to blocks).foreach(idx => banks((indexToReallocated + idx) % banks.length) += 1)
//      reallocationCount += 1
//    }
//    reallocationCount
//  }

  def reallocateHighest(banks: List[Int]): List[Int] = {
    val indexToReallocate = banks.indexOf(banks.max)
    val blocks = banks(indexToReallocate)
    val newBanks = banks.toArray
    newBanks(indexToReallocate) = 0
    (1 to blocks).foreach(idx => newBanks((indexToReallocate + idx) % newBanks.length) += 1)
    newBanks.toList
  }

  def part1(initialBanks: List[Int]): Int =
    Utils.indexesOfFirstDuplicate(Iterator.iterate(initialBanks)(reallocateHighest).toTraversable).get.apply(1)

  def part2(initialBanks: List[Int]): Int = {
    val indexesOfDuplicateStates = Utils.indexesOfFirstDuplicate(Iterator.iterate(initialBanks)(reallocateHighest).toTraversable).get
    indexesOfDuplicateStates(1) - indexesOfDuplicateStates(0)
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day06.txt").mkString.split("\t").map(_.toInt).toList
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
