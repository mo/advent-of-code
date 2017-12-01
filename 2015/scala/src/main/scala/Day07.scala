import scala.collection.mutable

object Day07 {

  type Circuit = mutable.HashMap[String, String]

  val InputSignal = """(\d+)""".r
  val WireConnection = """(\w+)""".r
  val AndGate = """(\w+) AND (\w+)""".r
  val OrGate = """(\w+) OR (\w+)""".r
  val LShiftGate = """(\w+) LSHIFT (\d+)""".r
  val RShiftGate = """(\w+) RSHIFT (\d+)""".r
  val NotGate = """NOT (\w+)""".r

  def parseCircuit(circuitStr: String): Circuit = {
    val circuit = new Circuit()
    for (Array(wireDefinition, wireName) <- circuitStr.split("\n").map(_.trim.split(" -> "))) {
      circuit(wireName) = wireDefinition
    }
    circuit
  }

  def calcWireSignal(circuit: Circuit, wire: String, overrides:  Map[String, Int]): Int = {
    var cachedWireSignals = overrides
    def calcWireSignalInternal(circuit: Circuit, wire: String): Int = {
      if (wire(0).isDigit) {
        wire.toInt
      } else {
        val signal = cachedWireSignals.getOrElse(wire, circuit(wire) match {
          case InputSignal(literalSignal) => literalSignal.toInt
          case WireConnection(wireIn) => calcWireSignalInternal(circuit, wireIn)
          case AndGate(wireA, wireB) => calcWireSignalInternal(circuit, wireA) & calcWireSignalInternal(circuit, wireB)
          case OrGate(wireA, wireB) => calcWireSignalInternal(circuit, wireA) | calcWireSignalInternal(circuit, wireB)
          case LShiftGate(wireIn, shift) => (calcWireSignalInternal(circuit, wireIn) << shift.toInt) & 0xFFFF
          case RShiftGate(wireIn, shift) => calcWireSignalInternal(circuit, wireIn) >>> shift.toInt
          case NotGate(wireIn) => (~calcWireSignalInternal(circuit, wireIn)) & 0xFFFF
        })
        cachedWireSignals += wire -> signal
        signal
      }
    }
    calcWireSignalInternal(circuit, wire)
  }

  def solve(): (Int, Int) = {
    val circuit = parseCircuit(DataFolder.openFile("day07.txt").mkString)
    val emptyMap = Map[String, Int]()
    val signalPart1 = calcWireSignal(circuit, "a", emptyMap)
    val signalPart2 = calcWireSignal(circuit, "a", Map("b" -> signalPart1))
    (signalPart1, signalPart2)
  }

  def main(args: Array[String]): Unit = {
    val (signalPart1, signalPart2) = solve()
    println("part1 wire a == " + signalPart1)
    println("part2 modified wire a == " + signalPart2)
  }

}
