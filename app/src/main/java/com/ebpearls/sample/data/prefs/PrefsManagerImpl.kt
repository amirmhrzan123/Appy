package com.ebpearls.sample.data.prefs

import android.content.SharedPreferences

class PrefsManagerImpl(private val pref: SharedPreferences) : PrefsManager {
    override fun setAccessToken(token: String) {
        pref.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String {
        return pref.getString(ACCESS_TOKEN, "")
    }
}