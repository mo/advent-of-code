import Day09.part2

object Day09 {

//  case class GameState(turnIdx: Int, marbles: List[Int], currentPosIdx: Int, playerScore: Map[Int, Int])
//  def slowPlay(playerCount: Int, highestMarbleValue: Int): Long = {
//    val initialState = GameState(
//      turnIdx = 1,
//      marbles = List(0),
//      currentPosIdx = 0,
//      playerScore = Map.empty[Int, Int].withDefaultValue(0)
//    )
//    Iterator.iterate(initialState) {
//      case GameState(turnIdx, marbles, currentPosIdx, playerScore) =>
//        val newMarble = turnIdx
//        if (turnIdx % 23 == 0) {
//          val currentPlayer = ((turnIdx - 1) % playerCount) + 1
//          val (before, after) = marbles.splitAt(1 + (currentPosIdx + marbles.length - 8) % marbles.length)
//          GameState(
//            turnIdx = turnIdx + 1,
//            marbles = before ++ after.tail,
//            currentPosIdx = before.length,
//            playerScore = playerScore + (currentPlayer -> (playerScore(currentPlayer) + newMarble + after.head))
//          )
//        } else {
//          val (before, after) = marbles.splitAt(1 + (currentPosIdx + 1) % marbles.length)
//          GameState(
//            turnIdx = turnIdx + 1,
//            marbles = before ++ List(newMarble) ++ after,
//            currentPosIdx = before.length,
//            playerScore = playerScore
//          )
//        }
//    }.slice(highestMarbleValue, highestMarbleValue + 1).toList.head.playerScore.values.max
//  }

  object Item {
    def apply[T](value: T): Item[T] = {
      val item = new Item(value, null, null)
      item.prev = item
      item.next = item
      item
    }
  }

  class Item[T](val value: T, var prev: Item[T], var next: Item[T]) {
    def append(value: T): Item[T] = {
      val item = new Item(value, this, next)
      next.prev = item
      next = item
      item
    }
    def remove(): Item[T] = {
      prev.next = next
      next.prev = prev
      this
    }
    def move(steps: Int): Item[T] = (1 to math.abs(steps)).foldLeft(this) {
      case (item, _) => if (steps.signum == -1) { item.prev } else { item.next }
    }
  }

  def play(playerCount: Int, lastMarble: Int): Long = {
    val playerScores = new Array[Long](playerCount)
    var currentMarble = Item(0L)

    (1 to lastMarble) foreach (turn => {
      if (turn % 23 == 0) {
        val currentPlayer = ((turn - 1) % playerCount) + 1
        val removed = currentMarble.move(-7).remove()
        currentMarble = removed.next
        playerScores(currentPlayer - 1) += removed.value + turn
      } else {
        currentMarble = currentMarble.next.append(turn)
      }
    })

    playerScores.max
  }

  def part1(input: String): Long = play(424, 71482)
  def part2(input: String): Long = play(424, 71482 * 100)

  def solve(): (Long, Long) = {
    val input = DataFolder.openFile("day09.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }
}
