import org.junit.Assert._
import org.junit.Test

class UtilsTests {

  @Test
  def indexesOfFirstDuplicateTest(): Unit = {
    assertEquals(List(1, 2), Utils.indexesOfFirstDuplicate(List(1, 2, 2, 3, 4)).get)
    assertEquals(List(1, 3), Utils.indexesOfFirstDuplicate(List(1, 2, 3, 2, 4)).get)
    assertEquals(List(0, 1), Utils.indexesOfFirstDuplicate(List(1, 1, 2, 2)).get)
    assertEquals(List(0, 2), Utils.indexesOfFirstDuplicate(List(1, 2, 1, 2)).get)
    assertEquals(None, Utils.indexesOfFirstDuplicate(List(1, 2, 3, 4)))
  }

}
