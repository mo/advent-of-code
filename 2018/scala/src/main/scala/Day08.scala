object Day08 {

  case class Node(children: List[Node], metadata: List[Int]) {
    def metasum: Int = metadata.sum + children.map(_.metasum).sum
    def value: Int = if (children.isEmpty) { metadata.sum } else { metadata.flatMap(idx => children.lift(idx - 1)).map(_.value).sum }
  }

  def parseNode(nums: List[Int]): (Node, List[Int]) = nums match {
    case childCount :: metadataCount :: tail =>
      val (childNodes, numsAfterChildNodes) = (1 to childCount).foldLeft((List.empty[Node], tail))((acc, _) => {
        val (newChildNode, remaining) = parseNode(acc._2)
        (acc._1 :+ newChildNode, remaining)
      })
      val metadata = numsAfterChildNodes.take(metadataCount)
      (Node(childNodes, metadata), numsAfterChildNodes.drop(metadataCount))
  }

  def part1(input: String): Int = parseNode(input.split(" ").map(_.toInt).toList)._1.metasum
  def part2(input: String): Int = parseNode(input.split(" ").map(_.toInt).toList)._1.value

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day08.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }
}
