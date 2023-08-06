package com.calibraint.roomdatabase.network

import com.calibraint.roomdatabase.model.UserData

object ApiService {

    suspend fun userData(onCompletion: (ArrayList<UserData>?) -> Unit, onError: (String) -> Unit) {
        val response = RetrofitHelper.getInstance().create(ApiInterface::class.java).getUserData()
        if (response.isSuccessful) {
            onCompletion(response.body())
        } else {
            onError(response.errorBody().toString())
        }
    }
}