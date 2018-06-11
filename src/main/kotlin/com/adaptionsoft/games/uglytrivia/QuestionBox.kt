package com.adaptionsoft.games.uglytrivia

import java.util.*

class QuestionBox (val categoryName: String){
    companion object {
        const val size = 50
    }

    var questions = LinkedList<String>()

    init {
        (0 until size).forEach {
            questions.addLast("${categoryName} Question $it")
        }
    }

    fun popQuestion(): String {
        return questions.removeFirst()
    }
}
