package com.adaptionsoft.games.uglytrivia

import com.adaptionsoft.games.uglytrivia.MoveResult.*

class Game {
    private var board = Board()

    fun add(playerName: String): Boolean {
        board.add(Player(playerName))

        println(playerName + " was added")
        println("They are player number " + board.numberOfPlayers)
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        val currentPlayer = board.currentPlayer
        println(currentPlayer.name + " is the current player")
        println("They have rolled a " + roll.value)

        val moveState = currentPlayer.move(roll)
        if (moveState == STUCK_IN_PENALTY_BOX) {
            println("${currentPlayer.name} is not getting out of the penalty box")
            return
        }

        if (moveState == GETTING_OUT_PENALTY_BOX) {
            println("${currentPlayer.name} is getting out of the penalty box")
        }

        val card = board.popQuestion()

        println("${currentPlayer.name}'s new location is ${currentPlayer.location}")
        println("The category is " + board.categoryName)
        println(card)
    }

    fun wasCorrectlyAnswered() {
        val currentPlayer = board.currentPlayer
        if (currentPlayer.lastMove != STUCK_IN_PENALTY_BOX) {
            currentPlayer.incrementScore()
            println("Answer was correct!!!!")
            println("${currentPlayer.name} now has ${currentPlayer.score} Gold Coins.")
        }
        board.advanceToNextPlayer()
    }

    fun wrongAnswer() {
        val currentPlayer = board.currentPlayer
        println("Question was incorrectly answered")
        println("${currentPlayer.name} was sent to the penalty box")

        currentPlayer.goesToPenaltyBox()

        board.advanceToNextPlayer()
    }

    fun isFinished(): Boolean = board.hasAWinner()
}