import org.junit.Test
import org.junit.Assert._

class Day22Tests {

  @Test
  def verifyExamplesPart1Fight1(): Unit = {
    var fightState = new Day22.FightState(playerHP = 10, playerMana = 250, bossHP = 13, bossDamage = 8)
    assertEquals(10, fightState.playerHP)
    assertEquals(250, fightState.playerMana)
    assertEquals(13, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_POISON, hard = false, debug = false)
    assertEquals(10, fightState.playerHP)
    assertEquals(77, fightState.playerMana)
    assertEquals(13, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(77, fightState.playerMana)
    assertEquals(10, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_MAGIC_MISSILE, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(24, fightState.playerMana)
    assertEquals(3, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(24, fightState.playerMana)
    assertEquals(0, fightState.bossHP)
  }

  @Test
  def verifyExamplesPart1Fight2(): Unit = {
    var fightState = new Day22.FightState(playerHP = 10, playerMana = 250, bossHP = 14, bossDamage = 8)
    assertEquals(10, fightState.playerHP)
    assertEquals(250, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_RECHARGE, hard = false, debug = false)
    assertEquals(10, fightState.playerHP)
    assertEquals(21, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(122, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_SHIELD, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(110, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(1, fightState.playerHP)
    assertEquals(211, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_DRAIN, hard = false, debug = false)
    assertEquals(3, fightState.playerHP)
    assertEquals(239, fightState.playerMana)
    assertEquals(12, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(340, fightState.playerMana)
    assertEquals(12, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_POISON, hard = false, debug = false)
    assertEquals(2, fightState.playerHP)
    assertEquals(167, fightState.playerMana)
    assertEquals(12, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(1, fightState.playerHP)
    assertEquals(167, fightState.playerMana)
    assertEquals(9, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_MAGIC_MISSILE, hard = false, debug = false)
    assertEquals(1, fightState.playerHP)
    assertEquals(114, fightState.playerMana)
    assertEquals(2, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = false, debug = false)
    assertEquals(1, fightState.playerHP)
    assertEquals(114, fightState.playerMana)
    assertEquals(-1, fightState.bossHP)
  }

  @Test
  def verifyFightInHardMode(): Unit = {
    var fightState = new Day22.FightState(playerHP = 10, playerMana = 250, bossHP = 14, bossDamage = 8)
    assertEquals(10, fightState.playerHP)
    assertEquals(250, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_RECHARGE, hard = true, debug = false)
    assertEquals(9, fightState.playerHP)
    assertEquals(21, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)
    assertEquals(1, fightState.playerHP)
    assertEquals(122, fightState.playerMana)
    assertEquals(14, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_SHIELD, hard = true, debug = false)
    assertEquals(0, fightState.playerHP)
    assertEquals(122, fightState.playerMana)
    assertEquals(14, fightState.bossHP)
  }

  @Test
  def verifyLowestManaCostPart2(): Unit = {
    var fightState = new Day22.FightState(playerHP = 50, playerMana = 500, bossHP = 51, bossDamage = 9)
    assertEquals(50, fightState.playerHP)
    assertEquals(500, fightState.playerMana)
    assertEquals(51, fightState.bossHP)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_POISON, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_DRAIN, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_RECHARGE, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_POISON, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_SHIELD, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_RECHARGE, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_POISON, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    fightState = Day22.doTurn(fightState, Day22.TurnAction.PLAYER_CASTS_MAGIC_MISSILE, hard = true, debug = false)
    fightState = Day22.doTurn(fightState, Day22.TurnAction.BOSS_ATTACKS, hard = true, debug = false)

    assertEquals(1216, fightState.totalManaSpent)
  }

  @Test
  def verifyCorrectAnswers(): Unit = {
    val (part1, part2) = Day22.solve()
    assertEquals(900, part1)
    assertEquals(1216, part2)
  }

}
