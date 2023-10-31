package com.ivanojok.myfarm.data.model

import androidx.room.Entity

data class SalesResponseData(
    var errorCode:Int,
    var message:String,
    var response: List<SalesResponse>
)

@Entity
data class SalesResponse(
    var id:String?,
    var animal_id:String?,
    var price:String?,
    var quantity:String?,
    var sold_by:String?,
    var sold_to:String?,
    var selling_date:String?
)

