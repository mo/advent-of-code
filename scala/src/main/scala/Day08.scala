import scala.util.matching.Regex

object Day08 {

  def unescapeHexByte(hexByte: String): String = {
    Integer.parseInt(hexByte, 16).toChar.toString.replace("$", "\\$")
  }

  def unescape(str: String): String = {
    val HEX_ESCAPE_PATTERN = """\\x([0-9a-f][0-9a-f])""".r
    val unescapedHexEscapes = HEX_ESCAPE_PATTERN.replaceAllIn(str, m => unescapeHexByte(m.group(1)))
    unescapedHexEscapes.replaceAllLiterally("""\\""", """\""").replaceAllLiterally("\\\"", "\"")
  }

  def escape(str: String): String = {
    "\"" + str.map {
      case '\\' => """\\"""
      case '"' => """\""""
      case default => default
    }.mkString  + "\""
  }

  def charactersOfCodeMinusCharactersInMemory(line: String): Int = {
    line.length - unescape(line.slice(1, line.length - 1)).length
  }

  def solve(): (Int, Int) = {
    val allLines = DataFolder.openFile("day08.txt").getLines().toList
    val part1 = allLines.map(charactersOfCodeMinusCharactersInMemory).sum
    val part2 = allLines.map(line => escape(line).length - line.length).sum
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
