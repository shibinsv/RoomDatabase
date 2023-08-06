package com.calibraint.roomdatabase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calibraint.roomdatabase.model.UserData
import com.calibraint.roomdatabase.network.ApiService
import com.calibraint.roomdatabase.room.AppDatabase
import com.calibraint.roomdatabase.room.User

class MainActivityViewModel() : ViewModel() {

    var userData = MutableLiveData<ArrayList<UserData>>()

    suspend fun userData(onError: (String) -> Unit) {
        ApiService.userData(
            onCompletion = { data ->
                Log.i("MainActivityViewModel", "userDataResponse: $data")
                userData.postValue(data)
            },
            onError = { error -> onError(error) }
        )
    }

    suspend fun updateData(database: AppDatabase, onCompletion: () -> Unit) {
        val userInfo = userData.value?.map { data ->
            User(
                userId = data.userId,
                name = data.name,
                email = data.email,
                mobile = data.mobile,
                gender = data.gender
            )
        }
        database.userDao().insertAll(userInfo)
        onCompletion()
    }
}