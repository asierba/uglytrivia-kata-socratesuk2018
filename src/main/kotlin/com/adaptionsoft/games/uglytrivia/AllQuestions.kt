package com.adaptionsoft.games.uglytrivia

class AllQuestions {
    private val popQuestions = QuestionBox("Pop")
    private val scienceQuestions = QuestionBox("Science")
    private val sportsQuestions = QuestionBox("Sports")
    private val rockQuestions = QuestionBox("Rock")

    private fun getCurrentQuestionBox(currentLocation:Number) = when (currentLocation) {
        0, 4, 8 -> popQuestions
        1, 5, 9 -> scienceQuestions
        2, 6, 10 -> sportsQuestions
        else -> rockQuestions
    }

    fun takeQuestion(currentLocation:Number): Question {
        return getCurrentQuestionBox(currentLocation).takeQuestion()
    }
}