import scala.collection.mutable.ListBuffer

object Day21 {

  val weapons = List(
    ("Dagger",      8, 4, 0),
    ("Shortsword", 10, 5, 0),
    ("Warhammer",  25, 6, 0),
    ("Longsword",  40, 7, 0),
    ("Greataxe",   74, 8, 0)
  )

  val armors = List(
    ("None",          0,     0,       0),
    ("Leather",      13,     0,       1),
    ("Chainmail",    31,     0,       2),
    ("Splintmail",   53,     0,       3),
    ("Bandedmail",   75,     0,       4),
    ("Platemail",   102,     0,       5)
  )

  val rings = List(
    ("None #1",       0,     0,       0),
    ("None #2",       0,     0,       0),
    ("Damage +1",    25,     1,       0),
    ("Damage +2",    50,     2,       0),
    ("Damage +3",   100,     3,       0),
    ("Defense +1",   20,     0,       1),
    ("Defense +2",   40,     0,       2),
    ("Defense +3",   80,     0,       3)
  )

  class Character(val name: String, var hitPoints: Int, val damage: Int, val armor: Int) {
    def attack(target: Character): Unit = {
      val damageDone = math.max(1, damage - target.armor)
      target.hitPoints = target.hitPoints - damageDone
      //println(s"The $name deals $damageDone damage; the ${target.name} goes down to ${target.hitPoints} hit points")
    }
  }

  def fight(player: Character, boss: Character): Boolean = {
    do {
      player.attack(boss)
      if (boss.hitPoints <= 0) {
        return true
      }
      boss.attack(player)
    } while (player.hitPoints > 0)
    false
  }

  def calcCostsAndOutcomes(): List[(Int, Boolean)] = {
    val costsAndOutcomes = ListBuffer[(Int, Boolean)]()
    for (weapon <- weapons; armor <- armors; ring1 <- rings; ring2 <- rings) {
      if (ring1 != ring2) {
        val totalCost = weapon._2 + armor._2 + ring1._2 + ring2._2
        val totalDamage = weapon._3 + armor._3 + ring1._3 + ring2._3
        val totalArmor = weapon._4 + armor._4 + ring1._4 + ring2._4
        val boss = new Character("boss", 109, 8, 2)
        val player = new Character("player", 100, totalDamage, totalArmor)
        costsAndOutcomes.append((totalCost, fight(player, boss)))
      }
    }
    costsAndOutcomes.toList
  }

  def solve(): (Int, Int) = {
    val costsAndOutcomes = calcCostsAndOutcomes()
    val part1 = costsAndOutcomes.filter(_._2 == true).map(_._1).min
    val part2 = costsAndOutcomes.filter(_._2 == false).map(_._1).max
    (part1, part2)
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")

  }

}
