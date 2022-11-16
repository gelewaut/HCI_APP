package com.example.fitlywebcompose.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkRoutineUser (

    @SerializedName("id")
    var id: Int?,
    @SerializedName("username")
    var username: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("avatarUrl")
    var avatarUrl: String,
    @SerializedName("date")
    var date: Date,
    @SerializedName("lastActivity")
    var lastActivity: Date,
)