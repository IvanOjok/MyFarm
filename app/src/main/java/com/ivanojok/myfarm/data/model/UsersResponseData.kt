package com.ivanojok.myfarm.data.model

data class UsersResponseData(
    var responseCode:Int?,
    var message:String?,
    var response: List<AuthResponse>
)
