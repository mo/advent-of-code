

object Day07 {

  type Node = String
  type Edge = (Node, Node)

  def parse(input: String): Seq[Edge] = {
    val line = """Step ([A-Z]) must be finished before step ([A-Z]) can begin.""".r
    val edges = input.split("\n").map {
      case line(from, to) => (from, to)
    }
    edges
  }

  class CyclicGraph extends Exception

  def tsort(edges: Seq[Edge]): Seq[Node] = {
    def tsortImpl(edges: Seq[Edge], alreadyEmittedNodes: Seq[Node]): Seq[Node] = {
      val fromNodes = edges.map(_._1).toSet
      val toNodes = edges.map(_._2).toSet
      val noDepNodes = fromNodes diff toNodes
      noDepNodes.toList.sorted match {
        case a :: _ =>
          val (remainingEdges, removedEdges) = edges.partition(e => e._1 != a)
          if (remainingEdges.nonEmpty) {
            tsortImpl(remainingEdges, alreadyEmittedNodes :+ a)
          } else {
            alreadyEmittedNodes ++ Seq(a) ++ removedEdges.map(_._2).distinct.sorted
          }
        case Nil => throw new CyclicGraph
      }
    }

    tsortImpl(edges, List())
  }

  def part1(input: String): String = tsort(parse(input)).mkString

  case class Task(node: Node, cost: Int)
  case class Worker(task: Option[Task], startTime: Option[Int]) {
    def isWorking(secondsPassedNow: Int): Boolean = startTime.exists(secondsPassedWhenStarted =>
      task.exists(t => secondsPassedNow - secondsPassedWhenStarted < t.cost)
    )
  }
  case class SimulationState(secondsPassed: Int, workers: List[Worker], remainingTasks: Set[Task], finishedTasks: Set[Task])
  def part2(workerCount: Int, nodeCost: Node => Int, input: String): Int = {
    val edges = parse(input)
    val q = (edges.map(_._1) ++ edges.map(_._2)).toSet
    val allTasks = q.map(node => Task(node, nodeCost(node)))
    val initialState = SimulationState(0, List.fill(workerCount)(Worker(None, None)), allTasks, Set())
    val simulationSteps = Iterator.iterate(initialState)(prevState => {
      val secondsPassed = prevState.secondsPassed + 1
      val (stillWorkingWorkers, idleWorkers) = prevState.workers.partition(_.isWorking(secondsPassed))
      val newFinishedTasks = prevState.finishedTasks ++ idleWorkers.flatMap(worker => worker.task).toSet
      val startableTasks = prevState.remainingTasks.filter(task =>
        !edges.exists(edge => edge._2 == task.node && !newFinishedTasks.exists(_.node == edge._1))
      ).toList.sortBy(_.node)
      val scheduledWorkers = (0 until workerCount - stillWorkingWorkers.length).map((newWorkerIdx: Int) =>
        if (newWorkerIdx < startableTasks.length) {
          Worker(Option(startableTasks(newWorkerIdx)), Some(secondsPassed))
        } else {
          Worker(None, None)
        }
      ).toList
      val scheduledTasks = scheduledWorkers.flatMap(_.task).toSet
      val newWorkers = scheduledWorkers ++ stillWorkingWorkers
      SimulationState(
        secondsPassed,
        newWorkers,
        prevState.remainingTasks diff scheduledTasks,
        newFinishedTasks
      )
    }).takeWhile(state => state.remainingTasks.nonEmpty || state.workers.exists(_.task.isDefined))
    simulationSteps.toList.last.secondsPassed
  }

  def solve(): (String, Int) = {
    val input = DataFolder.openFile("day07.txt").mkString
    (part1(input), part2(5, node => node(0).toInt - 4, input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }
}
