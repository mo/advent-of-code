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
  def verifyCorrectAnswers(): Unit = {
    val (signalPart1, signalPart2) = Day07.solve()
    assertEquals(3176, signalPart1)
    assertEquals(14710, signalPart2)
  }

}
