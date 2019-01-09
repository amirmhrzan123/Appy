package com.ebpearls.sample.di

import android.content.Context
import android.content.res.Resources
import com.ebpearls.sample.data.api.ApiServices
import com.ebpearls.sample.di.DataSourceProperties.SERVER_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * app module koin
 */
val appModule = module {
    single { provideResources(get()) }
    single { CoroutineScope(Dispatchers.Main + Job()) }

}

fun provideResources(context: Context): Resources = context.resources







