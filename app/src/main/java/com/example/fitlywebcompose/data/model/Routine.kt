package com.example.fitlywebcompose.data.model

import java.util.*

data class Routine(
    val id: Int,
    val name: String,
    val detail: String,
    val date: Int,
    val score: Int,
//    val isPublic: Boolean,
    val difficulty: String,
    var cycles: List<Cycle>
)

data class Cycle (
    val id: Int,
    val name: String,
    val detail: String,
    val type: String,
    val order: Int,
    val repetitions: Int,
    var exercises: List<CycleExercise>
)

data class CycleExercise (
    val order: Int,
    val duration: Int,
    val repetitions: Int,
    val exercise: Exercise
)

data class Exercise (
    val id: Int?,
    val name: String?,
    val detail: String?,
    val type: String?
)

enum class Difficulty {
    ROOKIE, BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
}

enum class CycleType {
    WARMUP, EXERCISE, COOLDOWN
}

enum class ExerciseType {
    EXERCISE, REST
}
