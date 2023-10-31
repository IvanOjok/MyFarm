package com.ivanojok.myfarm.data.model

import androidx.room.Entity

data class AnimalResponseData(
    var errorCode:Int?,
    var message:String?,
    var response: List<AnimalResponse>
)

@Entity
data class AnimalResponse(
    var id:String?,
    var name:String?,
    var tag_number:String?,
    var type:String?,
    var breed:String?,
    var image:String?,
    var age:String?,
    var gender:String?,
    var weight:String?,
    var color:String?
)


