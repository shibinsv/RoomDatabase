package com.calibraint.roomdatabase.model

import com.google.gson.annotations.SerializedName


data class UserData(
    @SerializedName("_id") val userId: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String
)