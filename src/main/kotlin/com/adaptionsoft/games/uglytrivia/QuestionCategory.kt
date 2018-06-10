package com.adaptionsoft.games.uglytrivia

import java.util.*

class QuestionCategory (val name: String){

    var questions = LinkedList<String>()

    init {
        generateDummyQuestions()
    }

    fun generateDummyQuestions() {
        (0..49).forEach {
            questions.addLast("$name Question $it")
        }
    }

    fun takeCard(): String {
        return questions.removeFirst()
    }
}
