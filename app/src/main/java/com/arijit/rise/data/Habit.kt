package com.arijit.rise.data

data class Habit(
    val title: String? = "Untitled",
    val description: String? = "No Description",
    val isCompleted: Boolean? = false,
    val emoji: String? = "\uD83C\uDFAF"
)