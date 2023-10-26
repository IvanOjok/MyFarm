package com.ivanojok.myfarm.data.model

data class SalesResponseData(
    var errorCode:Int,
    var message:String,
    var response: List<SalesResponse>
)

data class SalesResponse(
    var id:String?,
    var animal_id:String?,
    var price:String?,
    var quantity:String?,
    var sold_by:String?,
    var sold_to:String?,
    var selling_date:String?
)

