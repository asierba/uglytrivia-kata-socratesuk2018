package com.adaptionsoft.games.uglytrivia

class Game {
    var players = Players()

    val popCategory = QuestionCategory("Pop")
    val scienceCategory = QuestionCategory("Science")
    val sportsCategory = QuestionCategory("Sports")
    val rockCategory = QuestionCategory("Rock")

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + players.count())
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        println(currentPlayer().name + " is the current player")
        println("They have rolled a " + roll.value)

        if (currentPlayer().stuckInPenaltyBox(roll)) {
            println("${currentPlayer().name} is not getting out of the penalty box")
            currentPlayer().isGettingOutOfPenaltyBox = false
        }
        else {

            if (currentPlayer().isInPenaltyBox) {
                println("${currentPlayer().name} is getting out of the penalty box")
                currentPlayer().isGettingOutOfPenaltyBox = true
            }

            currentPlayer().move(roll)
            println("${currentPlayer().name}'s new location is ${currentPlayer().place}")
            println("The category is " + currentCategory().name)
            askQuestion()
        }

    }

    private fun askQuestion() {
        println(currentCategory().takeCard())
    }

    private fun currentCategory(): QuestionCategory = when (currentPlayer().place) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }

    fun wasCorrectlyAnswered(): Boolean {
        val player = currentPlayer()
        return if (player.isInPenaltyBox && !player.isGettingOutOfPenaltyBox) {
            players.nextPlayer()
            true
        } else {
            println("Answer was correct!!!!")
            player.incrementScore()
            println("${player.name} now has ${player.purse} Gold Coins.")
            players.nextPlayer()
            !player.isWinner()
        }
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(currentPlayer().name + " was sent to the penalty box")
        currentPlayer().isInPenaltyBox = true

        players.nextPlayer()
        return true
    }

    private fun currentPlayer() = players.getCurrentPlayer()

}