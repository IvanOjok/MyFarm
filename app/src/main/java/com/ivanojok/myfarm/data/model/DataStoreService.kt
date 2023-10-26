package com.ivanojok.myfarm.data.model

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreService {

    suspend fun read(context:Context): AuthResponse {
        val userFlow: Flow<AuthResponse> = context.userDataStore.data.map {
                it-> AuthResponse(
            it.id,
            it.fName,
            it.lName,
            it.title,
            it.phone,
            it.image,
            it.password,
            it.age
        )
        }

        return userFlow.first()
    }

        suspend fun writeToDataStore(user: AuthResponse, context: Context) {
            Log.d("user", "$user")
            context.userDataStore.updateData { currentUser ->
                currentUser.toBuilder()
                    .setId(user.id?:"")
                    .setFName(user.f_name?:"")
                    .setLName(user.l_name?:"")
                    .setTitle(user.title?:"")
                    .setPhone(user.phone?:"")
                    .setImage(user.image?:"")
                    .setPassword(user.password?:"")
                    .setAge(user.age.orEmpty())
                    .build()
            }
        }

}



