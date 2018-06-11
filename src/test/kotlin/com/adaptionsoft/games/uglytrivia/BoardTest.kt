package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertEquals
import org.junit.Test

class BoardTest {
    private val board = Board()

    @Test
    fun `has no players by default`() {
        assertEquals(0, board.numberOfPlayers)
    }

    @Test
    fun `can add a player`() {
        board.add(Player("Joe"))

        assertEquals(1, board.numberOfPlayers)
    }

    @Test
    fun `knows current player`() {
        board.add(Player("Joe"))

        assertEquals(board.currentPlayer, Player("Joe"))
    }

    @Test
    fun `moves to next player`() {
        board.add(Player("Joe"))
        board.add(Player("Mike"))
        board.advanceToNextPlayer()

        assertEquals(board.currentPlayer, Player("Mike"))
    }

    @Test
    fun `moves back to initial player`() {
        board.add(Player("Joe"))
        board.add(Player("Mike"))
        board.advanceToNextPlayer()
        board.advanceToNextPlayer()

        assertEquals(board.currentPlayer, Player("Joe"))
    }
}