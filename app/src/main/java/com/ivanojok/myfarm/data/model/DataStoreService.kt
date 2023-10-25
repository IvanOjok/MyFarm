package com.ivanojok.myfarm.data.model

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreService {

    fun read(context:Context) {
        val userFlow: Flow<AuthResponse> = context.userDataStore.data.map {
            user-> AuthResponse(
            user.id,
            user.fName,
            user.lName,
            user.title,
            user.phone,
            user.image,
            user.password,
            user.age
        )
        }
    }

        suspend fun writeToDataStore(user: AuthResponse, context: Context) {
            context.userDataStore.updateData { currentUser ->
                currentUser.toBuilder()
                    .setId(user.id)
                    .setFName(user.f_name)
                    .setLName(user.l_name)
                    .setTitle(user.title)
                    .setPhone(user.phone)
                    .setImage(user.image)
                    .setPassword(user.password)
                    .setAge(user.age)
                    .build()
            }
        }

}



