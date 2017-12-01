object Day11 {

  val alphabet = "abcdefghijklmnopqrstuvwxyz"

  def nextWord(word: String, alphabet: String): String = {
    val alphabetLastChar = alphabet.last
    word.toList match {
      case init :+ `alphabetLastChar` => nextWord(init.mkString, alphabet) + alphabet.head
      case init :+ ch => init.mkString + alphabet.charAt(alphabet.indexOf(ch) + 1).toString
      case Nil => alphabet(0).toString
    }
  }

  def nextWordRegularAlphabet(word: String): String = {
    nextWord(word, alphabet)
  }

  def containsIncreasingStraight(word: String): Boolean = {
    val increasingStraights = alphabet.sliding(3)
    increasingStraights.exists(word.contains(_))
  }

  def containsBadChars(word: String): Boolean = {
    "iol".exists(word.contains(_))
  }

  def containsPairs(word: String): Boolean = {
    """(\w)\1""".r.findAllIn(word).toSet.size >= 2
  }

  def nextPassword(password: String): String = {
    val startWord = nextWordRegularAlphabet(password)
    Iterator.iterate(startWord)(nextWordRegularAlphabet).filterNot(containsBadChars).filter(containsIncreasingStraight).filter(containsPairs).take(1).mkString
  }

  def solve(): (String, String) = {
    val part1 = nextPassword("cqjxjnds")
    val part2 = nextPassword(part1)
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
