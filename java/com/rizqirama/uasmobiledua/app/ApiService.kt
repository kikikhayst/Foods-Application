package com.rizqirama.uasmobiledua.app

import com.rizqirama.uasmobiledua.model.Food
import com.rizqirama.uasmobiledua.model.ResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("food")
    fun getFood():Call<ResponseModel>

    @POST("food")
    fun createFood(
        @Body data: Food
    ): Call<ResponseModel>

    @POST("food/{id")
    fun updateFood(
        @Path("id") id: Int,
        @Body data: Food
    ): Call<ResponseModel>

    @DELETE("food/{id}")
    fun deleteFood (
        @Path("id") id: Int
    ): Call<ResponseModel>
}