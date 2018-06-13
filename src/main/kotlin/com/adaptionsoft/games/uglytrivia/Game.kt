package com.adaptionsoft.games.uglytrivia

class Game {
    private var board = Board()

    fun add(playerName: String) {
        board.add(Player(playerName))
        displayPlayerAdded(playerName)
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        val currentPlayer = board.currentPlayer
        displayRollResult(currentPlayer, roll)

        currentPlayer.move(roll)

        if (currentPlayer.isStuckInPenaltyBox()) {
            displayPlayerStuckInPenaltyBox(currentPlayer)
            return
        }

        if (currentPlayer.isGettingOutOfPenaltyBox()) {
            displayPlayerGettingOut(currentPlayer)
        }

        displayPlayerLocation(currentPlayer)

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

    private fun displayPlayerLocation(currentPlayer: Player) {
        println("${currentPlayer.name}'s new location is ${currentPlayer.location}")
    }

    private fun displayPlayerGettingOut(currentPlayer: Player) {
        println("${currentPlayer.name} is getting out of the penalty box")
    }

    private fun displayPlayerStuckInPenaltyBox(currentPlayer: Player) {
        println("${currentPlayer.name} is not getting out of the penalty box")
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