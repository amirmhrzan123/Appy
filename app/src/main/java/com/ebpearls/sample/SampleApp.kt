package com.ebpearls.sample

import android.app.Application
import com.ebpearls.sample.di.*
import org.koin.android.ext.android.startKoin

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin(this, listOf(appModule, apiModule, persistenceDataModule, repositoryModule, viewModelModule), mapOf(), true)
    }
}