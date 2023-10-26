package com.ivanojok.myfarm.data.model

data class PurchasesResponsesData(
    var errorCode:Int,
    var message:String,
    var response: List<PurchasesResponse>
)

data class PurchasesResponse(
    var id:String?,
    var product:String?,
    var price:String?,
    var quantity:String?,
    var description:String?,
    var purchased_from:String?,
    var purchased_on:String?
)
