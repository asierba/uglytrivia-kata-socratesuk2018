package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    private val player = Player("Who Cares")

    @Test
    fun playerStartsInPlaceZero() {
        assertEquals(0, player.location)
    }

    @Test
    fun moveMovesThePlayerForwardFromSpaceZero() {
        player.move(Roll(3))

        assertEquals(3, player.location)
    }

    @Test
    fun moveMovesThePlayerFrowardFromNonZeroPlace() {
        player.move(Roll(3))
        player.move(Roll(2))

        assertEquals(5, player.location)
    }

    @Test
    fun moveWrapsAroundTheBoard() {
        player.move(Roll(6))
        player.move(Roll(5))
        player.move(Roll(3))

        assertEquals(2, player.location)
    }

    @Test
    fun `can increment the score`() {
        player.incrementScore()

        assertEquals(1, player.score)
    }

    @Test
    fun `wins when player has 6 points`() {
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()

        assertTrue(player.isWinner())
    }

    @Test
    fun `has not won if player has less than 6 points`() {
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()

        assertFalse(player.isWinner())
    }

    @Test(expected = CannotIncrementScore::class)
    fun `throws when score is incremented over 6`() {
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
        player.incrementScore()
    }

    @Test
    fun `player initially is not stuck penalty box`() {
        assertFalse(player.isStuckInPenaltyBox())
    }

    @Test
    fun `player is stuck in penalty box when rolls even number in a penalty box`() {
        player.goToPenaltyBox()

        player.move(Roll(2))

        assertTrue(player.isStuckInPenaltyBox())
    }

    @Test
    fun `player is not stuck in penalty box when rolls odd number in a penalty box`() {
        player.goToPenaltyBox()

        player.move(Roll(1))

        assertFalse(player.isStuckInPenaltyBox())
    }
}