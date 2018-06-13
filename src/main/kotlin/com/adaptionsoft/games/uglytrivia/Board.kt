package com.adaptionsoft.games.uglytrivia

class Board {
    companion object {
        const val size = 12
    }

    private var currentPlayerIndex = 0
    private val players = ArrayList<Player>()

    private val popQuestions = QuestionBox("Pop")
    private val scienceQuestions = QuestionBox("Science")
    private val sportsQuestions = QuestionBox("Sports")
    private val rockQuestions = QuestionBox("Rock")

    val numberOfPlayers get() = players.size
    val currentPlayer get() = players[currentPlayerIndex]

    private val questionBox get() = when (currentPlayer.location) {
        0, 4, 8 -> popQuestions
        1, 5, 9 -> scienceQuestions
        2, 6, 10 -> sportsQuestions
        else -> rockQuestions
    }

    fun add(player: Player) {
        players.add(player)
    }

    fun advanceToNextPlayer() {
        currentPlayerIndex++
        if (currentPlayerIndex == numberOfPlayers)
            currentPlayerIndex = 0
    }

    fun popQuestion() : Question {
        if (currentPlayer.isStuckInPenaltyBox())
            return NoQuestion()
        return questionBox.popQuestion()
    }

    fun hasAWinner(): Boolean = players.stream().anyMatch { it.isWinner() }
}