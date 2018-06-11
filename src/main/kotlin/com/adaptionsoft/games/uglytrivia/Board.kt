package com.adaptionsoft.games.uglytrivia

class Board {
    private var currentPlayerIndex = 0
    private val players = ArrayList<Player>()

    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    val categoryName get() = questionCategory.name
    val numberOfPlayers get() = players.size
    val currentPlayer get() = players[currentPlayerIndex]

    private val questionCategory get() = when (currentPlayer.location) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }

    fun add(player: Player) {
        players.add(player)
    }

    fun advanceToNextPlayer() {
        currentPlayerIndex++
        if (currentPlayerIndex == numberOfPlayers)
            currentPlayerIndex = 0
    }

    fun takeCard() : String = questionCategory.takeCard()
}