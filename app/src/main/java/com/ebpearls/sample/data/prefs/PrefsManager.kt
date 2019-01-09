package com.ebpearls.sample.data.prefs

interface PrefsManager {
    fun getAccessToken(): String
    fun setAccessToken(token: String)
}