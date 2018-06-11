package com.adaptionsoft.games.uglytrivia

import com.adaptionsoft.games.uglytrivia.MoveResult.*

data class Player(val name: String) {
    companion object {
        const val winningScore = 6
    }

    var location: Int = 0
    var score: Int = 0

    private var isInPenaltyBox: Boolean = false
    var lastMove: MoveResult = NORMAL_MOVE

    fun innerMove(roll: Roll) {
        location = (location + roll.value) % Board.size
    }

    fun move(roll: Roll): MoveResult {
        lastMove = getMoveResult(roll)
        if (lastMove != STUCK_IN_PENALTY_BOX) {
            innerMove(roll)
        }
        return lastMove
    }

    private fun getMoveResult(roll: Roll) : MoveResult {
        if (isInPenaltyBox) {
            if (roll.isEven()) {
                return STUCK_IN_PENALTY_BOX
            }
            return GETTING_OUT_PENALTY_BOX
        }
        return NORMAL_MOVE
    }

    fun incrementScore() {
        if(isWinner())
            throw CannotIncrementScore()
        score++
    }

    fun isWinner(): Boolean = score == winningScore

    fun goesToPenaltyBox() {
        isInPenaltyBox = true
    }
}

class CannotIncrementScore : Exception()