package com.example.fitlywebcompose.data.network.model

import com.example.fitlywebcompose.data.model.*
import com.example.fitlywebcompose.data.network.api.ApiDateTypeAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkRoutine(

    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("date"       ) var date       : Int,
    @SerializedName("score"      ) var score      : Int,
    @SerializedName("isPublic"   ) var isPublic   : Boolean? = null,
    @SerializedName("difficulty" ) var difficulty : String,
    @SerializedName("metadata"   ) var metadata   : String?  = null,
    @SerializedName("user"       ) var user       : User?    = null

) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            difficulty = difficulty,
            cycles = listOf()
        )
    }
}

class NetworkCycle (

    @SerializedName("id"          ) var id          : Int,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("detail"      ) var detail      : String,
    @SerializedName("type"        ) var type        : String,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("metadata"    ) var metadata    : String? = null

) {
    fun asModel() : Cycle {
        return Cycle (
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions,
            exercises = listOf()
        )
    }
}

class NetworkCycleExercise (

    @SerializedName("order"       ) var order       : Int,
    @SerializedName("duration"    ) var duration    : Int,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("exercise"    ) var exercise    : Exercise = NetworkExercise().asModel()

) {
    fun asModel() : CycleExercise {
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise
        )
    }
}

class NetworkExercise (

    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("detail"   ) var detail   : String? = null,
    @SerializedName("type"     ) var type     : String? = null,
    @SerializedName("date"     ) var date     : Int?    = null,
    @SerializedName("metadata" ) var metadata : String? = null

) {
    fun asModel() : Exercise {
        return Exercise(
            id = id,
            name = name,
            detail = detail,
            type = type
        )
    }
}