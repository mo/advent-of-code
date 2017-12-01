import org.junit.Test
import org.junit.Assert._

class Day07Tests {

  @Test
  def verifyExamplePart1(): Unit = {
    val circuitStr = """123 -> x
                        456 -> y
                        x AND y -> d
                        x OR y -> e
                        x LSHIFT 2 -> f
                        y RSHIFT 2 -> g
                        NOT x -> h
                        NOT y -> i"""

    val circuit = Day07.parseCircuit(circuitStr)
    val emptyMap = Map[String, Int]()
    assertEquals(72, Day07.calcWireSignal(circuit, "d", emptyMap))
    assertEquals(507, Day07.calcWireSignal(circuit, "e", emptyMap))
    assertEquals(492, Day07.calcWireSignal(circuit, "f", emptyMap))
    assertEquals(114, Day07.calcWireSignal(circuit, "g", emptyMap))
    assertEquals(65412, Day07.calcWireSignal(circuit, "h", emptyMap))
    assertEquals(65079, Day07.calcWireSignal(circuit, "i", emptyMap))
    assertEquals(123, Day07.calcWireSignal(circuit, "x", emptyMap))
    assertEquals(456, Day07.calcWireSignal(circuit, "y", emptyMap))
  }

  @Test
  def verifyAdditionalCases(): Unit = {
    val circuitStr = """32769 -> a
                        a LSHIFT 1 -> b"""
    val circuit = Day07.parseCircuit(circuitStr)
    assertEquals(2, Day07.calcWireSignal(circuit, "b", Map[String, Int]()))

    val circuitStr2 = """1 -> a
                         NOT a -> b"""
    val circuit2 = Day07.parseCircuit(circuitStr2)
    assertEquals(65534, Day07.calcWireSignal(circuit2, "b", Map[String, Int]()))
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (signalPart1, signalPart2) = Day07.solve()
    assertEquals(3176, signalPart1)
    assertEquals(14710, signalPart2)
  }

}
