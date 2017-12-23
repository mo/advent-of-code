import scala.collection.mutable

object Day18 {

  type Registers = mutable.Map[Char, Long]
  def Registers(): Registers = mutable.Map[Char, Long]()

  def parse(input: String): List[String] = {
    input.split("\n").toList
  }

  def executeInstruction(instruction: String, regs: Registers, onSnd: Long => Unit, onRcv: () => Option[Long], onJump: Int => Unit) : Boolean = {
    val snd = """snd (\w)""".r
    val setLiteral = """set (\w) (-?\d+)""".r
    val setRegister = """set (\w) (\w)""".r
    val addLiteral = """add (\w) (-?\d+)""".r
    val addRegister = """add (\w) (\w)""".r
    val mulLiteral = """mul (\w) (-?\d+)""".r
    val mulRegister = """mul (\w) (\w)""".r
    val modLiteral = """mod (\w) (-?\d+)""".r
    val modRegister = """mod (\w) (\w)""".r
    val rcv = """rcv (\w)""".r
    val jgzRegisterLiteral = """jgz (\w) (-?\d+)""".r
    val jgzRegisterRegister = """jgz (\w) (\w)""".r
    val jgzLiteralLiteral = """jgz (-?\d+) (-?\d+)""".r
    val jgzLiteralRegister = """jgz (-?\d+) (\w)""".r

    instruction match {
      case snd(reg) =>
        onSnd(regs(reg.head))
      case setLiteral(reg, value) =>
        regs(reg.head) = value.toInt
      case setRegister(reg1, reg2) =>
        regs(reg1.head) = regs(reg2.head)
      case addLiteral(reg, value) =>
        regs(reg.head) += value.toInt
      case addRegister(reg1, reg2) =>
        regs(reg1.head) += regs(reg2.head)
      case mulLiteral(reg, value) =>
        regs(reg.head) *= value.toInt
      case mulRegister(reg1, reg2) =>
        regs(reg1.head) *= regs(reg2.head)
      case modLiteral(reg, value) =>
        regs(reg.head) %= value.toInt
      case modRegister(reg1, reg2) =>
        regs(reg1.head) %= regs(reg2.head)
      case rcv(reg) =>
        onRcv() match {
          case None => return false
          case Some(value) => regs(reg.head) = value
        }
      case jgzRegisterLiteral(reg, value) if reg.head.isLetter =>
        if (regs(reg.head) > 0) { onJump(value.toInt - 1) }
      case jgzRegisterRegister(reg1, reg2) if reg1.head.isLetter && reg2.head.isLetter =>
        if (regs(reg1.head) > 0) { onJump(regs(reg2.head).toInt - 1) }
      case jgzLiteralLiteral(value1, value2) =>
        if (value1.toInt > 0) { onJump(value2.toInt - 1) }
      case jgzLiteralRegister(value, reg) if reg.head.isLetter =>
        if (value.toInt > 0) { onJump(regs(reg.head).toInt - 1) }
    }
    require(regs.keys.forall(_.isLetter))
    true
  }

  def runToFirstRcv(instructions: List[String]): Long = {
    val regs = Registers().withDefaultValue(0)
    var lastSnd = 0L
    var ip = 0
    while (true) {
      executeInstruction(instructions(ip), regs, sentValue => lastSnd = sentValue, () => return lastSnd, jmp => ip += jmp)
      ip += 1
    }
    0
  }

  def runTwoUntilTermination(instructions: List[String]): Int = {
    val regs0 = Registers().withDefaultValue(0)
    regs0.update('p', 0)
    val regs1 = Registers().withDefaultValue(0)
    regs1.update('p', 1)
    var ip0 = 0
    var ip1 = 0
    val messagesForP0 = mutable.Queue[Long]()
    val messagesForP1 = mutable.Queue[Long]()
    var p0waiting = false
    var p1waiting = false
    var p1sendCount = 0
    while (!(p0waiting && p1waiting)) {
      p0waiting = !executeInstruction(instructions(ip0), regs0, sentValue => messagesForP1.enqueue(sentValue), () => messagesForP0.dequeueFirst(_ => true), jmp => ip0 += jmp)
      p1waiting = !executeInstruction(instructions(ip1), regs1, sentValue => { p1sendCount += 1; messagesForP0.enqueue(sentValue) }, () => messagesForP1.dequeueFirst(_ => true), jmp => ip1 += jmp)
      if (!p0waiting) { ip0 += 1 }
      if (!p1waiting) { ip1 += 1 }
    }
    p1sendCount
  }

  def part1(input: String): Long = runToFirstRcv(parse(input))
  def part2(input: String): Int = runTwoUntilTermination(parse(input))

  def solve(): (Long, Int) = {
    val input = DataFolder.openFile("day18.txt").mkString
    (part1(input), part2(input))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
