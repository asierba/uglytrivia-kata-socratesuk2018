package com.adaptionsoft.games.trivia.runner

import com.adaptionsoft.games.uglytrivia.Game
import java.util.*

object GameRunner {
    var getRandom = {  Random() }
}

fun main(args: Array<String>) {
    val aGame = Game()


    aGame.add("Chet")
    aGame.add("Pat")
    aGame.add("Sue")

    val rand = GameRunner.getRandom()

    do {

        aGame.roll(rand.nextInt(5) + 1)

        if (rand.nextInt(9) == 7) {
            aGame.wrongAnswer()
        } else {
            aGame.wasCorrectlyAnswered()
        }


    } while (!aGame.isFinished())

}
