import scala.annotation.tailrec

object Day05 {

  def react(input: String): String = {
    var polymer = input
    input.toLowerCase.distinct.foreach(letter => {
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
    val reacted = react(input)
    reacted.distinct.map(dropLetter => {
      react(reacted.filter(ch => ch != dropLetter && ch != dropLetter.toUpper)).length
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
