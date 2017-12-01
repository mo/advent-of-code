object Day05 {

  def isNicePart1(str: String): Boolean = {
    val vowels = "aeiou".toSet
    val forbiddenPairs = Set("ab", "cd", "pq", "xy")
    def containsDuplicateChar(st: String) = """(.)\1""".r.findFirstIn(st).isDefined

    str.count(vowels) >= 3 && containsDuplicateChar(str) && !str.sliding(2).exists(forbiddenPairs)
  }

  def containsDuplicatePair(str: String): Boolean = """(\w)(\w).*\1\2""".r.findFirstIn(str).isDefined
  def containsXYX(str: String): Boolean = str.sliding(3).exists(ss => ss.length == 3 && ss(0) == ss(2))

  def isNicePart2(str: String): Boolean = {
    containsDuplicatePair(str) && containsXYX(str)
  }

  def solve(): (Int, Int) = {
    val strings = DataFolder.openFile("day05.txt").getLines().toList

    val niceStringsSeenPart1 = strings.count(isNicePart1)
    val niceStringsSeenPart2 = strings.count(isNicePart2)

    (niceStringsSeenPart1, niceStringsSeenPart2)
  }

  def main(args: Array[String]): Unit = {
    val (niceStringsSeenPart1, niceStringsSeenPart2) = solve()
    println("nice strings, part 1: " + niceStringsSeenPart1)
    println("nice strings, part 2: " + niceStringsSeenPart2)
  }

}
