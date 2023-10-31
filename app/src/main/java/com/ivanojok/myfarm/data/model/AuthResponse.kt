package com.ivanojok.myfarm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AuthResponseData(
    var errorCode:Int?,
    var message:String?,
    var response: AuthResponse
)

@Entity
data class AuthResponse(
    @PrimaryKey var id:String,
    var f_name:String?,
    var l_name:String?,
    var title:String?,
    var phone:String?,
    var image:String?,
    var password:String?,
    var age:String?
)
