import scala.util.Try

object Day08 {

  case class Condition(register: String, operator: String, value: Int)
  case class Instruction(register: String, delta: Int, condition: Condition)

  def parse(input: String): List[Instruction] = {
    val instructionLine = """(\w+) (\w+) (\-?\d+) if (\w+) ([<>=!]+) (\-?\d+)""".r
    input.split("\n").map {
      case instructionLine(targetReg, incDec, delta, condReg, condOp, condVal) =>
        val incDecFactor = if (incDec == "dec") { -1 } else { 1 }
        Instruction(targetReg, incDecFactor * delta.toInt, Condition(condReg, condOp, condVal.toInt))
    }.toList
  }

  def isConditionTrue(condition: Condition, regs: Map[String, Int]): Boolean = condition match {
    case Condition(regName, "<", value) => regs(regName) < value
    case Condition(regName, ">", value) => regs(regName) > value
    case Condition(regName, "<=", value) => regs(regName) <= value
    case Condition(regName, ">=", value) => regs(regName) >= value
    case Condition(regName, "==", value) => regs(regName) == value
    case Condition(regName, "!=", value) => regs(regName) != value
  }

  def run(instructions: List[Instruction]): List[Map[String, Int]] = {
    val initialRegs = Map[String, Int]().withDefaultValue(0)
    instructions
      .scanLeft(initialRegs)((regs, instruction) => {
        if (isConditionTrue(instruction.condition, regs)) {
          regs.updated(instruction.register, regs(instruction.register) + instruction.delta)
        } else {
          regs
        }
      })
  }

  def part1(input: String): Int = run(parse(input)).last.values.max
  def part2(input: String): Int = run(parse(input)).map(regs => Try(regs.values.max).getOrElse(0)).max

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
