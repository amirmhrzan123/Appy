package com.ebpearls.sample.di

import com.ebpearls.sample.data.api.ApiServices
import com.ebpearls.sample.data.prefs.PrefsManager
import com.ebpearls.sample.data.room.dao.UserDao
import com.ebpearls.sample.ui.login.LoginRepository
import com.ebpearls.sample.ui.login.LoginRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module.module

val repositoryModule = module {
    single { provideLoginRepository(get(), get(), get(),get()) as LoginRepository }
}

fun provideLoginRepository(prefsManager: PrefsManager, apiServices: ApiServices, userDao: UserDao,viewModelScope:CoroutineScope): LoginRepositoryImpl {
    return LoginRepositoryImpl(prefsManager, apiServices, userDao,viewModelScope)
}