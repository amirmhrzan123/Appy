package com.ebpearls.sample.base

import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.util.regex.Pattern

 class BaseResponse<T> {
     @SerializedName("message")
     var message: String? = null

     @SerializedName("data")
     var results: T? = null

 }




