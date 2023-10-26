package com.ivanojok.myfarm.data.retrofit

import com.ivanojok.myfarm.data.model.AuthResponseData
import com.ivanojok.myfarm.data.model.PurchasesResponsesData
import com.ivanojok.myfarm.data.model.SalesResponseData
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ResponseInterface {

    @FormUrlEncoded
    @POST("login.php")
    //field map
    //suspend fun loginUser(@FieldMap data:HashMap<String, Any>): AuthResponseData

    //using fields
    suspend fun loginUser(@Field("phone") phone:String, @Field("password") password:String): AuthResponseData


    @GET("get_purchases.php")
    suspend fun getPurchases(): PurchasesResponsesData

    @GET("get_sales.php")
    suspend fun getSales(): SalesResponseData
}