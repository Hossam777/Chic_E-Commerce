package com.app.chic_ecommerce.common.data

import android.app.Activity
import android.content.Context

class SharedPreferences {
    fun writeString(activity: Activity, key: String, txt: String){
        val preferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(preferences.edit()) {
            putString(key, txt)
            apply()
        }
    }

    fun readString(activity: Activity, key: String): String {
        val preferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return preferences.getString(key, "").toString()
    }
}