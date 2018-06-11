package com.adaptionsoft.games.uglytrivia

data class Player(val name: String, var location: Int = 0) {
    private val boardSize = 12
    private val winningScore = 6

    var isGettingOutOfPenaltyBox: Boolean = false
    var isInPenaltyBox: Boolean = false
    var score: Int = 0

    fun move(roll: Roll) {
        location = (location + roll.value) % boardSize
    }

    fun incrementScore() {
        if(isWinner())
            throw CannotIncrementScore()

        score++
    }


    fun isWinner(): Boolean = score == winningScore

    fun stuckInPenaltyBox(roll: Roll): Boolean {
        return isInPenaltyBox && roll.isEven()
    }

    fun staysInPenaltyBox() =
            isInPenaltyBox && !isGettingOutOfPenaltyBox

    fun goesToPenaltyBox() {
        isInPenaltyBox = true
    }
}

class CannotIncrementScore : Exception()