package com.example.fitlywebcompose.data.network.model

import com.example.fitlywebcompose.data.model.*
import com.example.fitlywebcompose.data.network.api.ApiDateTypeAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkRoutine(

    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("date"       ) var date       : Date,
    @SerializedName("score"      ) var score      : Int,
    @SerializedName("isPublic"   ) var isPublic   : Boolean,
    @SerializedName("difficulty" ) var difficulty : String,
    @SerializedName("metadata"   ) var metadata   : String?  = null,
    @SerializedName("user"       ) var networkRoutineUser: NetworkRoutineUser,

) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            difficulty = difficulty,
            cycles = emptyList()
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
            exercises = emptyList()
        )
    }
}

class NetworkCycleExercise (

    @SerializedName("order"       ) var order       : Int,
    @SerializedName("duration"    ) var duration    : Int,
    @SerializedName("repetitions" ) var repetitions : Int,
    @SerializedName("exercise"    ) var networkExercise: NetworkExercise,

) {
    fun asModel() : CycleExercise {
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = networkExercise.asModel()
        )
    }
}

class NetworkExercise (

    @SerializedName("id"       ) var id       : Int,
    @SerializedName("name"     ) var name     : String,
    @SerializedName("detail"   ) var detail   : String,
    @SerializedName("type"     ) var type     : String,
    @SerializedName("date"     ) var date     : Date,
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