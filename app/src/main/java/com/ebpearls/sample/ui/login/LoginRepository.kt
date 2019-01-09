package com.ebpearls.sample.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebpearls.sample.base.BaseResponse
import com.ebpearls.sample.data.api.ApiServices
import com.ebpearls.sample.data.prefs.PrefsManager
import com.ebpearls.sample.data.room.dao.UserDao
import com.ebpearls.sample.Resource
import com.ebpearls.sample.data.api.ApiResponse
import com.ebpearls.sample.data.api.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


interface LoginRepository {
    fun fetchPersonalDetail(request: LoginRequest): LiveData<Resource<LoginResponse>>
    fun doLogin(request: LoginRequest): LiveData<Resource<LoginResponse>>


}

class LoginRepositoryImpl(private val prefs: PrefsManager, private val apiService: ApiServices, private val useDao: UserDao, private val viewModelScope: CoroutineScope) : LoginRepository {

    override fun doLogin(request: LoginRequest): LiveData<Resource<LoginResponse>> {
        val loginResponse = MutableLiveData<Resource<LoginResponse>>()
        GlobalScope.launch {
            try {
                loginResponse.postValue(Resource.loading(null))
                val response = apiService.doLogin(request).await()
                loginResponse.postValue(Resource.success(response.results))

            } catch (e: Throwable) {

                loginResponse.postValue(Resource.error(e.localizedMessage, null))
            }
        }

        return loginResponse
    }




    override fun fetchPersonalDetail(request: LoginRequest): LiveData<Resource<LoginResponse>> {
        return object : NetworkBoundResource<LoginResponse, BaseResponse<LoginResponse>>(viewModelScope) {
            override fun saveCallResult(item: BaseResponse<LoginResponse>) {
                useDao.insert(item.results!!)
            }

            override fun shouldFetch(data: LoginResponse?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<LoginResponse> {
                return useDao.getUserById()
            }

            override fun createCall(): LiveData<ApiResponse<BaseResponse<LoginResponse>>> {
                return apiService.fetchProfile(request)
            }


        }.asLiveData()
    }


}