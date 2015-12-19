import org.json4s.JsonAST.{JValue, JString, JInt, JObject, JArray}
import org.json4s.native.JsonMethods.parse

object Day12 {

  def sumAllNumbers(str: String): Int = {
    "([0-9-][0-9]*)".r.findAllIn(str).map(_.toInt).sum
  }

  def sumNonRedNumbers(node: JValue): Int = {
    node match {
      case JArray(arr) => arr.map(sumNonRedNumbers).sum
      case JObject(keyValueTuples) =>
        if (!keyValueTuples.exists(_._2 == JString("red")))
          keyValueTuples.map(_._2).map(sumNonRedNumbers).sum
        else
          0
      case JInt(value) => value.toInt
      case _ => 0
    }
  }

  def solve(): (Int, Int) = {
    val jsonData = DataFolder.openFile("day12.txt").mkString
    (sumAllNumbers(jsonData), sumNonRedNumbers(parse(jsonData)))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println("part1 == " + part1)
    println("part2 == " + part2)
  }

}
