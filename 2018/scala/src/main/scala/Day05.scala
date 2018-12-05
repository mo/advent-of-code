import scala.annotation.tailrec

object Day05 {

//  @tailrec
//  def react(polymer: String): String = {
//    println("len=" + polymer.length)
//    reactOnce(polymer) match {
//      case (false, _) => polymer
//      case (true, newPolymer) => react(newPolymer)
//    }
//  }
//
//  def reactOnce(polymer: String): (Boolean, String) = polymer.toList match {
//    case a :: b :: tail if a.isLower != b.isLower && a.toLower == b.toLower =>
//      (true, tail.mkString)
//    case a :: tail =>
//      val (reacted, newPolymer) = reactOnce(tail.mkString)
//      (reacted, a + newPolymer)
//    case Nil =>
//      (false, "")
//  }
//  def part1(input: String): Int = react(input).length

  def react(input: String): String = {
    var polymer = input
    ('a' to 'z').foreach(letter => {
      val uLetter = letter.toUpper
      polymer = polymer.replaceAll("" + letter + uLetter, "")
      polymer = polymer.replaceAll("" + uLetter + letter, "")
    })
    if (polymer == input) {
      polymer
    } else {
      react(polymer)
    }
  }

  def part1(input: String): Int = react(input).length
  def part2(input: String): Int = {
    ('a' to 'z').map(dropLetter => {
      react(input.filter(ch => ch != dropLetter && ch != dropLetter.toUpper)).length
    }).min
  }

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day05.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
