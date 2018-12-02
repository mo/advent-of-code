import java.io.File
import java.nio.file.Paths

object DataFolder extends App {
  def openFile(filename: String): io.BufferedSource = {
    val dataFile = Paths.get(System.getProperty("user.dir"), "../data", filename).toFile
    io.Source.fromFile(dataFile)
  }
}
