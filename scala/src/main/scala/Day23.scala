object Day23 {

  val HLF = """hlf (\w+)""".r
  val TPL = """tpl (\w+)""".r
  val INC = """inc (\w+)""".r
  val JMP = """jmp ([+-][1-9][0-9]*)""".r
  val JIE = """jie (\w+), ([+-][1-9][0-9]*)""".r
  val JIO = """jio (\w+), ([+-][1-9][0-9]*)""".r

  def runProgram(instructionsStr: String, initialRegisters: Map[String, Long]): Map[String, Long] = {
    val instructions = instructionsStr.split("\n")
    var ip = 0
    var registers = initialRegisters
    while (ip < instructions.length) {
      require(ip >= 0)
      instructions(ip) match {
        case HLF(registerName) =>
          require(registers(registerName) >= 0, registers(registerName))
          require(registers(registerName) % 2 == 0)
          registers += registerName -> registers(registerName) / 2
          ip += 1
        case TPL(registerName) =>
          require(registers(registerName) >= 0)
          registers += registerName -> 3 * registers(registerName)
          ip += 1
        case INC(registerName) =>
          registers += registerName -> (registers(registerName) + 1)
          ip += 1
        case JMP(offset) =>
          ip += offset.toInt
        case JIE(registerName, offset) =>
          if (registers(registerName) % 2 == 0) {
            ip += offset.toInt
          } else {
            ip += 1
          }
        case JIO(registerName, offset) =>
          if (registers(registerName) == 1) {
            ip += offset.toInt
          } else {
            ip += 1
          }
        case line =>
          throw new IllegalStateException("invalid instruction: " + line)
      }
    }
    registers
  }

  def solve(): (Long, Long) = {
    val instructionsStr = DataFolder.openFile("day23.txt").mkString

    var registers = Map("a" -> 0L, "b" -> 0L)
    registers = runProgram(instructionsStr, registers)
    val part1 = registers("b")

    registers = Map("a" -> 1L, "b" -> 0L)
    registers = runProgram(instructionsStr, registers)

    val part2 = registers("b")
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
