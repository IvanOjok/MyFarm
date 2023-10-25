package com.ivanojok.myfarm.data.model

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.ivanojok.myfarm.UserModel
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserModel> {
    override val defaultValue: UserModel = UserModel.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserModel {
        try {
            return UserModel.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserModel,
        output: OutputStream
    ) = t.writeTo(output)
}

val Context.userDataStore: DataStore<UserModel> by dataStore(
    fileName = "user.proto",
    serializer = UserSerializer
)