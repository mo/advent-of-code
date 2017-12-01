object Day01 {

  object FacingDirection extends Enumeration {
    type FacingDirection = Value
    val EAST, NORTH, SOUTH, WEST = Value
  }

  object TurnDirection extends Enumeration {
    type TurnDirection = Value
    val LEFT, RIGHT = Value
    def fromString(st: String): TurnDirection = {
      st match {
        case "L" => TurnDirection.LEFT
        case "R" => TurnDirection.RIGHT
      }
    }
  }
  
  case class Position(x: Int, y: Int, facingDirection: FacingDirection.FacingDirection)

  def turn(facingDirection: FacingDirection.FacingDirection, turnDirection: TurnDirection.TurnDirection): FacingDirection.FacingDirection = {
    turnDirection match {
      case TurnDirection.LEFT => facingDirection match {
        case FacingDirection.EAST => FacingDirection.NORTH
        case FacingDirection.NORTH => FacingDirection.WEST
        case FacingDirection.SOUTH => FacingDirection.EAST
        case FacingDirection.WEST => FacingDirection.SOUTH
      }
      case TurnDirection.RIGHT => facingDirection match {
        case FacingDirection.EAST => FacingDirection.SOUTH
        case FacingDirection.NORTH => FacingDirection.EAST
        case FacingDirection.SOUTH => FacingDirection.WEST
        case FacingDirection.WEST => FacingDirection.NORTH
      }
    }
  }

  def followInstruction(startPosition: Position, instruction: String): Position = {
    val newFacingDirection = turn(startPosition.facingDirection, TurnDirection.fromString(instruction.substring(0, 1)))
    val steps = instruction.substring(1).toInt
    newFacingDirection match {
      case FacingDirection.EAST => Position(startPosition.x + steps, startPosition.y, newFacingDirection)
      case FacingDirection.NORTH => Position(startPosition.x, startPosition.y + steps, newFacingDirection)
      case FacingDirection.SOUTH => Position(startPosition.x, startPosition.y - steps, newFacingDirection)
      case FacingDirection.WEST => Position(startPosition.x - steps, startPosition.y, newFacingDirection)
    }
  }

  def followPath(startPosition: Position, path: List[String]): Position = {
    path match {
      case h :: t =>
        val newPosition = followInstruction(startPosition, h)
        followPath(newPosition, t)
      case Nil => startPosition
    }
  }

  def stepsMoved(startPosition: Position, path: List[String]): Int = {
    val newPosition = followPath(startPosition, path)
    math.abs(startPosition.x - newPosition.x) + math.abs(startPosition.y - newPosition.y)
  }

  def firstVisitedTwice(startPosition: Position, path: List[String]): Position = {
    def firstVisitedTwiceInternal(startPosition: Position, path: List[String], initialAlreadyVisited: List[(Int, Int)]): Position = {
      path match {
        case instruction :: restOfInstructions =>
          val newFacingDirection = turn(startPosition.facingDirection, TurnDirection.fromString(instruction.substring(0, 1)))
          val steps = instruction.substring(1).toInt
          var currentPosition = startPosition
          var alreadyVisited = initialAlreadyVisited
          for (step <- 1 to steps) {
            currentPosition = newFacingDirection match {
              case FacingDirection.EAST => Position(currentPosition.x + 1, currentPosition.y, newFacingDirection)
              case FacingDirection.NORTH => Position(currentPosition.x, currentPosition.y + 1, newFacingDirection)
              case FacingDirection.SOUTH => Position(currentPosition.x, currentPosition.y - 1, newFacingDirection)
              case FacingDirection.WEST => Position(currentPosition.x - 1, currentPosition.y, newFacingDirection)
            }
            if (alreadyVisited.contains((currentPosition.x, currentPosition.y))) {
              return currentPosition
            }
            alreadyVisited = alreadyVisited :+ (currentPosition.x,currentPosition.y)
          }
          firstVisitedTwiceInternal(currentPosition, restOfInstructions, alreadyVisited)
        case Nil => throw new RuntimeException("No position visited twice")
      }
    }
    firstVisitedTwiceInternal(startPosition, path, List())
  }

  def stepsToFirstVisitedTwice(startPosition: Position, path: List[String]): Int = {
    val newPosition = firstVisitedTwice(startPosition, path)
    math.abs(startPosition.x - newPosition.x) + math.abs(startPosition.y - newPosition.y)
  }

  def solve(): (Int, Int) = {
    val instructions = DataFolder.openFile("day01.txt").mkString.split(",").map(_.trim).toList
    val part1 = stepsMoved(Position(0, 0, FacingDirection.NORTH), instructions)
    val part2 = stepsToFirstVisitedTwice(Position(0, 0, FacingDirection.NORTH), instructions)
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
