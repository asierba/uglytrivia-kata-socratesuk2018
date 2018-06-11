package com.adaptionsoft.games.uglytrivia

class Game {
    var board = Board()

    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    fun add(playerName: String): Boolean {
        board.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + board.numberOfPlayers)
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
            println("${currentPlayer().name}'s new location is ${currentPlayer().location}")
            println("The category is " + board.categoryName)
            println(board.takeCard())
        }

    }

    private fun currentPlayer() = board.currentPlayer

    fun wasCorrectlyAnswered(): Boolean {
        var notAWinner = true

        if (!currentPlayer().staysInPenaltyBox()) {
            currentPlayer().incrementScore()
            println("Answer was correct!!!!")
            println("${currentPlayer().name} now has ${currentPlayer().score} Gold Coins.")
            notAWinner = !currentPlayer().isWinner()
        }
        board.advanceToNextPlayer()

        return notAWinner

    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println("${currentPlayer().name} was sent to the penalty box")

        currentPlayer().goesToPenaltyBox()

        board.advanceToNextPlayer()
        return true
    }


}