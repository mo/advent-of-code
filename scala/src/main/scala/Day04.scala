import java.security.MessageDigest

object Day04 {

  def md5(s: String) = MessageDigest.getInstance("MD5").digest(s.getBytes)
  def bytesToHex(bytes: Array[Byte]): String = bytes.map("%02X" format _).mkString

  def calcAdventCoinNumber(zeros: Int, key: String) : Int = {
    var num = 1
    while (!bytesToHex(md5(key + num)).startsWith("0" * zeros)) {
      num += 1
    }
    num
  }

  def solve(): (Int, Int) = {
    (calcAdventCoinNumber(5, "ckczppom"), calcAdventCoinNumber(6, "ckczppom"))
  }

  def main(args: Array[String]): Unit = {
    val (fiveZeroNum, sixZeroNum) = solve()
    println("the 5-zero advent coin number for 'ckczppom' is: " + fiveZeroNum)
    println("the 6-zero advent coin number for 'ckczppom' is: " + sixZeroNum)
  }
}
