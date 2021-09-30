package com.ab13hay.coinfo.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface MyApi {

    @GET("latest")
    fun userUpdate(): Call<ResponseBody>

    companion object{
        operator fun invoke(
        ): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://api.rootnet.in/covid19-in/stats/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}