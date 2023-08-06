package com.calibraint.roomdatabase.network

import com.calibraint.roomdatabase.model.UserData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/c979ab58afa044a59106ef641ff5ad6c/userData")
    suspend fun getUserData() : Response<ArrayList<UserData>>
}