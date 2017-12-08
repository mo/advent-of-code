import scala.annotation.tailrec
import scala.collection.{TraversableLike, mutable}

object Utils {

  def indexesOfFirstDuplicate[T](items: Traversable[T]): Option[List[Int]] = indexesOfFirstNTuple(items, 2)

  def indexesOfFirstNTuple[T](items: Traversable[T], n: Int): Option[List[Int]] = {
    @tailrec
    def recurse(items: Traversable[T], currentIndex: Int, seenAtIndexes: mutable.HashMap[T, mutable.ListBuffer[Int]]): Option[List[Int]] = {

      items.headOption match {
        case Some(elem) =>
          val seenIndexesList = seenAtIndexes.getOrElseUpdate(elem, new mutable.ListBuffer)
          seenIndexesList += currentIndex
          if (seenIndexesList.length == n) {
            Some(seenIndexesList.toList)
          } else {
            recurse(items.tail, currentIndex + 1, seenAtIndexes)
          }
        case None => None
      }
    }
    recurse(items, 0, mutable.HashMap())
  }

}
