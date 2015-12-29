import scala.collection.mutable.ListBuffer

object Day22 {

  object TurnAction extends Enumeration {
    type TurnAction = Value
    val
      PLAYER_CASTS_MAGIC_MISSILE,
      PLAYER_CASTS_DRAIN,
      PLAYER_CASTS_SHIELD,
      PLAYER_CASTS_POISON,
      PLAYER_CASTS_RECHARGE,
      BOSS_ATTACKS = Value
  }

  case class FightState(
    playerHP: Int,
    playerMana: Int,
    bossHP: Int,
    bossDamage: Int,
    shieldTimer: Int = 0,
    poisonTimer: Int = 0,
    rechargeTimer: Int = 0,
    turnHistory: List[TurnAction.TurnAction] = List[TurnAction.TurnAction](),
    totalManaSpent: Int = 0
  )

  def doTurn(fightState: FightState, action: TurnAction.TurnAction, hard: Boolean, debug: Boolean): FightState = {
    var nextPlayerHP = fightState.playerHP
    if (action != TurnAction.BOSS_ATTACKS) {
      if (debug) println("-- Player turn --")
      if (hard) {
        nextPlayerHP -= 1
        if (nextPlayerHP == 0) {
          return new FightState(
            playerHP = nextPlayerHP,
            playerMana = fightState.playerMana,
            bossHP = fightState.bossHP,
            bossDamage = fightState.bossDamage,
            shieldTimer = fightState.shieldTimer,
            poisonTimer = fightState.poisonTimer,
            rechargeTimer = fightState.rechargeTimer,
            turnHistory = fightState.turnHistory ++ List(action),
            totalManaSpent = fightState.totalManaSpent
          )
        }
      }
    } else {
      if (debug) println("-- Boss turn --")
    }
    val playerArmor = if (fightState.shieldTimer > 0) 7 else 0
    if (debug) println(s"- Player has $nextPlayerHP hit points, $playerArmor armor, ${fightState.playerMana} mana")
    if (debug) println(s"- Boss has ${fightState.bossHP} hit points")

    val nextShieldTimer = if (fightState.shieldTimer > 0) {
      if (debug) println(s"Shield's timer is now ${fightState.shieldTimer - 1}.")
      fightState.shieldTimer - 1
    } else {
      0
    }

    var nextBossHP = fightState.bossHP
    val nextPoisonTimer = if (fightState.poisonTimer > 0) {
      nextBossHP -= 3
      if (nextBossHP > 0) {
        if (debug) println(s"Poison deals 3 damage; its timer is now ${fightState.poisonTimer - 1}.")
      } else {
        if (debug) println(s"Poison deals 3 damage. This kills the boss, and the player wins.")
      }
      fightState.poisonTimer - 1
    } else {
      0
    }

    var nextMana = fightState.playerMana
    val nextRechargeTimer = if (fightState.rechargeTimer > 0) {
      nextMana += 101
      if (debug) println(s"Recharge provides 101 mana; its timer is now ${fightState.rechargeTimer - 1}.")
      fightState.rechargeTimer - 1
    } else {
      0
    }

    action match {
      case TurnAction.PLAYER_CASTS_MAGIC_MISSILE =>
        require(nextMana >= 53)
        if (debug) println("Player casts Magic Missile, dealing 4 damage.")
        if (debug) println()
        new FightState(
          playerHP = nextPlayerHP,
          playerMana = nextMana - 53,
          bossHP = nextBossHP - 4,
          bossDamage = fightState.bossDamage,
          shieldTimer = nextShieldTimer,
          poisonTimer = nextPoisonTimer,
          rechargeTimer = nextRechargeTimer,
          turnHistory = fightState.turnHistory ++ List(action),
          totalManaSpent = fightState.totalManaSpent + 53
        )
      case TurnAction.PLAYER_CASTS_DRAIN =>
        require(nextMana >= 73)
        if (debug) println("Player casts Drain, dealing 2 damage, and healing 2 hit points.")
        if (debug) println()
        new FightState(
          playerHP = nextPlayerHP + 2,
          playerMana = nextMana - 73,
          bossHP = nextBossHP - 2,
          bossDamage = fightState.bossDamage,
          shieldTimer = nextShieldTimer,
          poisonTimer = nextPoisonTimer,
          rechargeTimer = nextRechargeTimer,
          turnHistory = fightState.turnHistory ++ List(action),
          totalManaSpent = fightState.totalManaSpent + 73
        )
      case TurnAction.PLAYER_CASTS_SHIELD =>
        require(nextShieldTimer == 0 && nextMana >= 113)
        if (debug) println("Player casts Shield, increasing armor by 7.")
        if (debug) println()
        new FightState(
          playerHP = nextPlayerHP,
          playerMana = nextMana - 113,
          bossHP = nextBossHP,
          bossDamage = fightState.bossDamage,
          shieldTimer = 6,
          poisonTimer = nextPoisonTimer,
          rechargeTimer = nextRechargeTimer,
          turnHistory = fightState.turnHistory ++ List(action),
          totalManaSpent = fightState.totalManaSpent + 113
        )
      case TurnAction.PLAYER_CASTS_POISON =>
        require(nextPoisonTimer == 0 && nextMana >= 173)
        if (debug) println("Player casts Poison.")
        if (debug) println()
        new FightState(
          playerHP = nextPlayerHP,
          playerMana = nextMana - 173,
          bossHP = nextBossHP,
          bossDamage = fightState.bossDamage,
          shieldTimer = nextShieldTimer,
          poisonTimer = 6,
          rechargeTimer = nextRechargeTimer,
          turnHistory = fightState.turnHistory ++ List(action),
          totalManaSpent = fightState.totalManaSpent + 173
        )
      case TurnAction.PLAYER_CASTS_RECHARGE =>
        require(nextRechargeTimer == 0 && nextMana >= 229)
        if (debug) println("Player casts Recharge.")
        if (debug) println()
        new FightState(
          playerHP = nextPlayerHP,
          playerMana = nextMana - 229,
          bossHP = nextBossHP,
          bossDamage = fightState.bossDamage,
          shieldTimer = nextShieldTimer,
          poisonTimer = nextPoisonTimer,
          rechargeTimer = 5,
          turnHistory = fightState.turnHistory ++ List(action),
          totalManaSpent = fightState.totalManaSpent + 229
        )
      case TurnAction.BOSS_ATTACKS =>
        if (nextBossHP <= 0) {
          if (debug) println()
          new FightState(
            playerHP = nextPlayerHP,
            playerMana = nextMana,
            bossHP = nextBossHP,
            bossDamage = fightState.bossDamage,
            shieldTimer = nextShieldTimer,
            poisonTimer = nextPoisonTimer,
            rechargeTimer = nextRechargeTimer,
            turnHistory = fightState.turnHistory,
            totalManaSpent = fightState.totalManaSpent
          )
        } else {
          val bossDamage = math.max(1, fightState.bossDamage - playerArmor)
          if (debug) println(s"Boss attacks for $bossDamage damage.")
          if (debug) println()
          new FightState(
            playerHP = nextPlayerHP - bossDamage,
            playerMana = nextMana,
            bossHP = nextBossHP,
            bossDamage = fightState.bossDamage,
            shieldTimer = nextShieldTimer,
            poisonTimer = nextPoisonTimer,
            rechargeTimer = nextRechargeTimer,
            turnHistory = fightState.turnHistory,
            totalManaSpent = fightState.totalManaSpent
          )
        }
    }
  }

  def smallestManaToWin(initialPlayerHP: Int, initialPlayerMana: Int, initialBossHP: Int, bossDamage: Int, hard: Boolean, debug: Boolean): Int = {
    var allOngoingFights = ListBuffer(new FightState(initialPlayerHP, initialPlayerMana, initialBossHP, bossDamage))
    while (true) {
      // Fire ze missiles!
      allOngoingFights = allOngoingFights.flatMap {
        fightState =>
          val newFights = ListBuffer[FightState]()
          if (fightState.playerMana >= 53 || fightState.rechargeTimer > 0) { newFights += doTurn(fightState, TurnAction.PLAYER_CASTS_MAGIC_MISSILE, hard, debug) }
          if (fightState.playerMana >= 73 || fightState.rechargeTimer > 0) { newFights += doTurn(fightState, TurnAction.PLAYER_CASTS_DRAIN, hard, debug) }
          if ((fightState.playerMana >= 113 || (fightState.rechargeTimer > 0 && fightState.playerMana >= 113 - 101)) && fightState.shieldTimer <= 1) { newFights += doTurn(fightState, TurnAction.PLAYER_CASTS_SHIELD, hard, debug) }
          if ((fightState.playerMana >= 173 || (fightState.rechargeTimer > 0 && fightState.playerMana >= 173 - 101)) && fightState.poisonTimer <= 1) { newFights += doTurn(fightState, TurnAction.PLAYER_CASTS_POISON, hard, debug) }
          if (fightState.playerMana >= 229 && fightState.rechargeTimer <= 1) { newFights += doTurn(fightState, TurnAction.PLAYER_CASTS_RECHARGE, hard, debug) }
          newFights.toList
      }

      // When playing "hard" we might have died during the player turn
      allOngoingFights = allOngoingFights.filter(_.playerHP > 0)

      // TRICKY CASE: We don't check if we killed yet boss yet because the boss might die from poison during his turn
      // and in that case we might have won with an yet lower total mana spend. Therefore we keep the "ongoing" fights
      // where player killed the boss for just a bit longer (until the boss plays one more turn).
      allOngoingFights = allOngoingFights.filter(_.bossHP <= 0) ++ allOngoingFights.filter(_.bossHP > 0).map(doTurn(_, TurnAction.BOSS_ATTACKS, hard, debug))

      // Now, we can check if we've killed the boss, either directly or if we died from poison during his turn.
      allOngoingFights = allOngoingFights.sortBy(_.totalManaSpent)
      allOngoingFights.toList.find(state => state.bossHP <= 0 && state.playerHP > 0) match {
        case Some(state) => return state.totalManaSpent
        case _ =>
      }

      // Dying is the last thing you'll ever do
      allOngoingFights = allOngoingFights.filter(_.playerHP > 0)
    }
    -1
  }

  def solve(): (Int, Int) = {
    (smallestManaToWin(50, 500, 51, 9, hard = false, debug = false), smallestManaToWin(50, 500, 51, 9, hard = true, debug = false))
  }

  def main(args: Array[String]): Unit = {
    val (part1, part2) = solve()
    println(s"part1 == $part1")
    println(s"part2 == $part2")
  }

}
