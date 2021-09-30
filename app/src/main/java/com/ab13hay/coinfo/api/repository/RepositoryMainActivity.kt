package com.ab13hay.coinfo.api.repository

import androidx.lifecycle.MutableLiveData
import com.ab13hay.coinfo.api.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryMainActivity {
    fun userLogin(): MutableLiveData<String> {
        val Response = MutableLiveData<String>()
        MyApi().userUpdate()
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Response.value = response.body()?.string()
                    } else {
                        Response.value = response.errorBody()?.string()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Response.value = t.message
                }
            })
        return Response
    }
}