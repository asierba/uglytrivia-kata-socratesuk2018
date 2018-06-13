package com.adaptionsoft.games.uglytrivia

class Game {
    private var board = Board()

    fun add(playerName: String): Boolean {
        board.add(Player(playerName))

        displayPlayerAdded(playerName)
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        val currentPlayer = board.currentPlayer
        displayRollResult(currentPlayer, roll)

        currentPlayer.move(roll)

        if (currentPlayer.isStuckInPenaltyBox()) {
            println("${currentPlayer.name} is not getting out of the penalty box")
            return
        }

        if (currentPlayer.isGettingOutOfPenaltyBox()) {
            println("${currentPlayer.name} is getting out of the penalty box")
        }

        println("${currentPlayer.name}'s new location is ${currentPlayer.location}")

        val question = board.popQuestion()
        displayQuestionDetails(question)
    }

    fun wasCorrectlyAnswered() {
        if (!board.currentPlayer.isStuckInPenaltyBox()) {
            board.currentPlayer.incrementScore()
            displayCorrectAnswer(board.currentPlayer)
        }
        board.advanceToNextPlayer()
    }

    fun wrongAnswer() {
        displayWrongAnswer(board.currentPlayer)
        board.currentPlayer.goesToPenaltyBox()
        board.advanceToNextPlayer()
    }

    fun isFinished(): Boolean = board.hasAWinner()

    private fun displayPlayerAdded(playerName: String) {
        println("$playerName was added")
        println("They are player number " + board.numberOfPlayers)
    }

    private fun displayQuestionDetails(question: String) {
        println("The category is " + board.categoryName)
        println(question)
    }

    private fun displayRollResult(currentPlayer: Player, roll: Roll) {
        println("${currentPlayer.name} is the current player")
        println("They have rolled a ${roll.value}")
    }

    private fun displayCorrectAnswer(currentPlayer: Player) {
        println("Answer was correct!!!!")
        println("${currentPlayer.name} now has ${currentPlayer.score} Gold Coins.")
    }

    private fun displayWrongAnswer(currentPlayer: Player) {
        println("Question was incorrectly answered")
        println("${currentPlayer.name} was sent to the penalty box")
    }
}