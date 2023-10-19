package com.ivanojok.myfarm.data.model

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun showError(t: Throwable): String {

    val x = when(t) {
        is SocketTimeoutException -> "Connection Time out"
        is ConnectException -> "No Internet Access"
        is UnknownHostException -> "Unable To connect to Server"
        is HttpException -> {
            val reader = t.response()?.errorBody()?.charStream()
            val error = Gson().fromJson(reader, ErrorResponse::class.java)
            error.message

//            t.response()?.errorBody()?.charStream()?.let {
//                val obj = Gson().fromJson(it, GenericErrorBody::class.java)
//                t = obj.message ?: "An error occurred ${this.message()}"
//            }
//            t.toString()
        }
        else -> "An error occurred"
    }
     return x ?: "Unknown Error"
}

