import java.security.MessageDigest

object Day04 {

  def md5(s: String) = MessageDigest.getInstance("MD5").digest(s.getBytes)

  def calcAdventCoinNumber(zeros: Int, key: String) : Int = {
    Iterator.from(0).indexWhere {
      i =>
        val hash = md5(key + i)
        hash(0) == 0 && hash.slice(0, 1 + zeros / 2).flatMap(byte => Seq(byte >> 4, byte & 0xF)).slice(0, zeros).forall(_ == 0)
    }
  }

  def solve(): (Int, Int) = {
    (calcAdventCoinNumber(5, "ckczppom"), calcAdventCoinNumber(6, "ckczppom"))
  }

  def main(args: Array[String]): Unit = {
    val (fiveZeroNum, sixZeroNum) = solve()
    println(s"the 5-zero advent coin number for 'ckczppom' is: $fiveZeroNum")
    println(s"the 6-zero advent coin number for 'ckczppom' is: $sixZeroNum")
  }

}
