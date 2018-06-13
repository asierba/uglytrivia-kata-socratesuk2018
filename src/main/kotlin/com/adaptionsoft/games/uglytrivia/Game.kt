package com.adaptionsoft.games.uglytrivia

class Game {
    private var board = Board()

    fun add(playerName: String) {
        val player = Player(playerName)
        board.add(player)
        displayPlayerAdded(player)
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        val currentPlayer = board.currentPlayer
        displayRollResult(currentPlayer, roll)

        currentPlayer.move(roll)
        val question = board.popQuestion()

        displayStatus(currentPlayer)
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
        board.currentPlayer.goToPenaltyBox()
        board.advanceToNextPlayer()
    }

    fun isFinished(): Boolean = board.hasAWinner()

    private fun displayPlayerAdded(player: Player) {
        println("${player.name} was added")
        println("They are player number " + board.numberOfPlayers)
    }

    private fun displayStatus(player: Player) {
        if (player.isStuckInPenaltyBox()) {
            println("${player.name} is not getting out of the penalty box")
            return
        }
        if (player.isGettingOutOfPenaltyBox()) {
            println("${player.name} is getting out of the penalty box")
        }
        println("${player.name}'s new location is ${player.location}")
    }

    private fun displayQuestionDetails(question: Question) {
        if(question is NoQuestion) return

        println("The category is " + question.category)
        println(question.value)
    }

    private fun displayRollResult(currentPlayer: Player, roll: Roll) {
        println("${currentPlayer.name} is the current player")
        println("They have rolled a ${roll.value}")
    }

    private fun displayCorrectAnswer(player: Player) {
        println("Answer was correct!!!!")
        println("${player.name} now has ${player.score} Gold Coins.")
    }

    private fun displayWrongAnswer(player: Player) {
        println("Question was incorrectly answered")
        println("${player.name} was sent to the penalty box")
    }
}