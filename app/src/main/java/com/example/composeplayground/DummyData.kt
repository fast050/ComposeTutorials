package com.example.composeplayground

object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        AnswerChoice(
            "Lexi",
            "Test...Test...Test...",
             R.drawable.rick1
        ),
        AnswerChoice(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim(),
            R.drawable.rick2
        ),
        AnswerChoice(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim(),
            R.drawable.rick3
        ),
        AnswerChoice(
            "Lexi",
            "Searching for alternatives to XML layouts...",
            R.drawable.rick4
        )
    )
}

data class AnswerChoice(
    val author:String="empty",
    val body:String="empty",
    val image:Int = R.drawable.ic_launcher_background,
    var isSelected :Boolean =false
)