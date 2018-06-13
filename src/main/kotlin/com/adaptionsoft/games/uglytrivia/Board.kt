package com.adaptionsoft.games.uglytrivia

class Board {
    companion object {
        const val size = 12
    }

    private var currentPlayerIndex = 0
    private val players = ArrayList<Player>()
    private val allQuestions = AllQuestions()

    val numberOfPlayers get() = players.size
    val currentPlayer get() = players[currentPlayerIndex]

    fun add(player: Player) {
        players.add(player)
    }

    fun advanceToNextPlayer() {
        currentPlayerIndex++
        if (currentPlayerIndex == numberOfPlayers)
            currentPlayerIndex = 0
    }

    fun takeQuestion() : Question {
        if (currentPlayer.isStuckInPenaltyBox())
            return NoQuestion()

        return allQuestions.takeQuestion(currentPlayer.location)
    }

    fun hasAWinner(): Boolean = players.stream().anyMatch { it.isWinner() }
}
