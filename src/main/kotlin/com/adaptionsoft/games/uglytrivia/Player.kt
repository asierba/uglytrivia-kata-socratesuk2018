package com.adaptionsoft.games.uglytrivia

data class Player(val name: String) {
    companion object {
        const val winningScore = 6
    }

    var location: Int = 0
    var score: Int = 0
    private var isInPenaltyBox: Boolean = false
    private var lastRoll: Roll = Roll.noRoll()

    fun move(roll: Roll) {
        if (!isStuckInPenaltyBox(roll)) {
            moveLocation(roll)
        }

        this.lastRoll = roll
    }

    private fun moveLocation(roll: Roll) {
        location = (location + roll.value) % Board.size
    }

    fun incrementScore() {
        if(isWinner())
            throw CannotIncrementScore()
        score++
    }

    fun isWinner(): Boolean = score == winningScore

    fun goToPenaltyBox() {
        isInPenaltyBox = true
    }

    fun isStuckInPenaltyBox(): Boolean = isStuckInPenaltyBox(lastRoll)

    private fun isStuckInPenaltyBox(roll:Roll): Boolean {
        return isInPenaltyBox && roll.isEven()
    }

    fun isGettingOutOfPenaltyBox(): Boolean {
        return isInPenaltyBox && !lastRoll.isEven()
    }

}

class CannotIncrementScore : Exception()