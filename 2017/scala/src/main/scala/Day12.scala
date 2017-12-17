import Day12.findConnectedPrograms

import scala.annotation.tailrec

object Day12 {

  type Graph = Map[Int, List[Int]]

  def parse(input: String): Graph = input.split("\n")
    .map(_.split("[^\\d]+").toList.map(_.toInt))
    .map { case prog :: pipes => prog -> pipes }
    .toMap

  def findConnectedPrograms(graph: Graph, startProgId: Int): Set[Int] = {
    def impl(graph: Graph, shouldVisit: List[Int], visited: Set[Int]): Set[Int] =
      shouldVisit match {
        case Nil => visited
        case first :: remaining =>
          impl(graph, remaining ++ graph(first).filterNot(visited), visited + first)
      }
    impl(graph, List(startProgId), Set())
  }

  def groups(graph: Graph): List[Set[Int]] = {
    @tailrec
    def impl(graph: Graph, foundGroups: List[Set[Int]], startProgId: Int): List[Set[Int]] = {
      val group = findConnectedPrograms(graph, startProgId)
      val notReachable = graph.keys.toSet diff (group ++ foundGroups.flatMap(_.toList))
      notReachable.headOption match {
        case None =>
          foundGroups :+ group
        case Some(progId) =>
          impl(graph, foundGroups :+ group, progId)
      }
    }

    impl(graph, List(), 0)
  }

  def part1(input: String): Int = findConnectedPrograms(parse(input), 0).size
  def part2(input: String): Int = groups(parse(input)).length

  def solve(): (Int, Int) = {
    val input = DataFolder.openFile("day12.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
