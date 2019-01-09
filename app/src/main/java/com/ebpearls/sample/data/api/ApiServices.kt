package com.ebpearls.sample.data.api

import androidx.lifecycle.LiveData
import com.ebpearls.sample.base.BaseResponse
import com.ebpearls.sample.ui.login.LoginRequest
import com.ebpearls.sample.ui.login.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *network data source retrofit
 */
interface ApiServices {
    @POST(EndPoint.API.LOGIN)
    fun fetchProfile(@Body loginRequest: LoginRequest): LiveData<ApiResponse<BaseResponse<LoginResponse>>>

    @POST(EndPoint.API.LOGIN)
    fun doLogin(@Body loginRequest: LoginRequest): Deferred<BaseResponse<LoginResponse>>
}

class EndPoint {
    object API {
        const val LOGIN = "auth/login"
    }
}