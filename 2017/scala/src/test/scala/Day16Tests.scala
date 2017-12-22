import org.junit.Assert._
import org.junit.Test

class Day16Tests {

  @Test
  def verifyBasics(): Unit = {
    assertEquals("cdeab", Day16.dance("abcde", Day16.parse("s3")))

    assertEquals("eabcd", Day16.dance("abcde", Day16.parse("s1")))
    assertEquals("eabdc", Day16.dance("eabcd", Day16.parse("x3/4")))
    assertEquals("baedc", Day16.dance("eabdc", Day16.parse("pe/b")))

    assertEquals("baedc", Day16.dance("abcde", Day16.parse("s1,x3/4,pe/b")))
  }

  @Test
  def verifyCorrectAnswers(): Unit = assertEquals(("dcmlhejnifpokgba", "ifocbejpdnklamhg"), Day16.solve())

}
