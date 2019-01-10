package com.ebpearls.sample.di

import com.ebpearls.sample.ui.login.LoginViewModel
import com.ebpearls.sample.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}

