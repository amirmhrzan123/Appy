package com.ebpearls.sample.ui.login

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import com.ebpearls.sample.Resource
import com.ebpearls.sample.base.BaseViewModel

class LoginViewModel(private val loginRepository: LoginRepository, @StringRes val resources: Resources) : BaseViewModel<LoginNavigator>() {


    fun doLogin(): LiveData<Resource<LoginResponse>> {
//        return loginRepository.fetchPersonalDetail(LoginRequest())
        return loginRepository.fetchPersonalDetail(LoginRequest())


//        viewModelScope.launch {
//            withContext(IO) {
//                //                isLoadingLiveData.postValue(LoadingStatus(resources.getString(R.string.login_in), true))
//                try {
//                    userLiveData = loginRepository.fetchPersonalDetail(LoginRequest()) as MutableLiveData<Resource<LoginResponse>>
////                    getNavigator().onLoginSuccess()
////                    withContext(Main) { isLoadingLiveData.value = LoadingStatus("", false)
////                    }
//
//
//                } catch (exp: Throwable) {
//                    exp.stackTrace
////                    withContext(Main) { isLoadingLiveData.value = LoadingStatus("", false) }
//                } finally {
//
//                }
//            }
//        }

    }
}