package com.example.fitlywebcompose.data.network.model

import com.google.gson.annotations.SerializedName

 class NetworkReviewContent (
    @SerializedName("score")
    var score: Int,
    @SerializedName("review")
    var review: String? = null
)