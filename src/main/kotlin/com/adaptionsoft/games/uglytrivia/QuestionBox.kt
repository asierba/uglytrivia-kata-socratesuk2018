package com.adaptionsoft.games.uglytrivia

import java.util.*

class QuestionBox (private val categoryName: String){
    companion object {
        const val size = 50
    }

    var questions = LinkedList<Question>()

    init {
        (0 until size).forEach {
            questions.addLast(Question("$categoryName Question $it", categoryName))
        }
    }

    fun popQuestion(): Question {
        return questions.removeFirst()
    }
}


open class Question(val value:String, val category:String)

class NoQuestion : Question("", "")
