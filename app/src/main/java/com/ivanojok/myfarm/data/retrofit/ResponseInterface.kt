package com.ivanojok.myfarm.data.retrofit

import com.ivanojok.myfarm.data.model.AddWorkerResponse
import com.ivanojok.myfarm.data.model.AnimalResponseData
import com.ivanojok.myfarm.data.model.AuthResponseData
import com.ivanojok.myfarm.data.model.PurchasesResponsesData
import com.ivanojok.myfarm.data.model.SalesResponseData
import com.ivanojok.myfarm.data.model.UsersResponseData
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
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


    @GET("get_users.php")
    suspend fun getUsers(): UsersResponseData

    @GET("get_animals.php")
    suspend fun getAnimals(): AnimalResponseData

    @FormUrlEncoded
    @POST("insert_user.php")
    suspend fun addWorker(@FieldMap data:HashMap<String, Any?>, @Header("Authorization") token:String): AddWorkerResponse

}
