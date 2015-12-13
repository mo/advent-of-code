import scala.collection.immutable.HashSet

object Day05 {

  val VOWELS = HashSet('a', 'e', 'i', 'o', 'u')

  def containsAtleastThreeVowels(str: String): Boolean = {
    var vowelCount = 0
    for (ch <- str) {
      if (VOWELS.contains(ch)) {
        vowelCount += 1
      }
    }
    vowelCount >= 3
  }

  def containsTwiceInARowLetter(str: String): Boolean = {
    (0 to str.length-2).exists(idx => str(idx) == str(idx + 1))
  }

  def containsBadSubstrings(str: String): Boolean = {
    (str contains "ab") || (str contains "cd") || (str contains "pq") || (str contains "xy")
  }

  def isNicePart1(str: String): Boolean = {
    containsAtleastThreeVowels(str) && containsTwiceInARowLetter(str) && !containsBadSubstrings(str)
  }

  def containsNonOverlappingDoublePair(str: String): Boolean = {
    str.length >= 4 && (0 to str.length-4).exists(idx => str.slice(idx + 2, str.length) contains str.slice(idx, idx + 2))
  }

  def containsXYX(str: String): Boolean = {
    str.length >= 3 && (0 to str.length-3).exists(idx => str(idx) == str(idx + 2))
  }

  def isNicePart2(str: String): Boolean = {
    containsNonOverlappingDoublePair(str) && containsXYX(str)
  }

  def solve(): (Int, Int) = {
    var niceStringsSeenPart1 = 0
    var niceStringsSeenPart2 = 0
    for (line <- DataFolder.openFile("day05.txt").getLines()) {
      if (isNicePart1(line)) {
        niceStringsSeenPart1 += 1
      }
      if (isNicePart2(line)) {
        niceStringsSeenPart2 += 1
      }
    }

    (niceStringsSeenPart1, niceStringsSeenPart2)
  }

  def main(args: Array[String]): Unit = {
    val (niceStringsSeenPart1, niceStringsSeenPart2) = solve()
    println("nice strings, part 1: " + niceStringsSeenPart1)
    println("nice strings, part 2: " + niceStringsSeenPart2)
  }

}
