package com.ebpearls.sample.ui.profile

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import com.ebpearls.sample.Resource
import com.ebpearls.sample.base.BaseViewModel
import com.ebpearls.sample.ui.login.LoginNavigator
import com.ebpearls.sample.ui.login.LoginRepository
import com.ebpearls.sample.ui.login.LoginRequest
import com.ebpearls.sample.ui.login.LoginResponse
import kotlinx.coroutines.cancel

class ProfileViewModel(private val loginRepository: LoginRepository, @StringRes val resources: Resources) : BaseViewModel<LoginNavigator>() {


    fun fetchPersonalInfo(): LiveData<Resource<LoginResponse>> {
//        return loginRepository.fetchPersonalDetail(LoginRequest())
        return loginRepository.fetchPersonalDetail(LoginRequest())


    }

    override fun onCleared() {
        super.onCleared()
        loginRepository.getScope().cancel()
    }
}
