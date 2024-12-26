package com.bsuir.recreation_facility.app.model.setting

import android.content.Context
import com.bsuir.sportdiary.app.setting.AppSettings

class SharedPreferencesAppSettings(
    appContext: Context
) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    // Token

    override fun setCurrentToken(token: String?) {
        val editor = sharedPreferences.edit()
        if (token == null)
            editor.remove(PREF_CURRENT_ACCOUNT_TOKEN)
        else
            editor.putString(PREF_CURRENT_ACCOUNT_TOKEN, token)
        editor.apply()
    }

    override fun getCurrentToken(): String? =
        sharedPreferences.getString(PREF_CURRENT_ACCOUNT_TOKEN, null)

    // Username

    override fun getCurrentUsername(): String? =
        sharedPreferences.getString(PREF_CURRENT_ACCOUNT_USERNAME, null)


    override fun setCurrentUsername(username: String?) {
        val editor = sharedPreferences.edit()
        if (username == null)
            editor.remove(PREF_CURRENT_ACCOUNT_USERNAME)
        else
            editor.putString(PREF_CURRENT_ACCOUNT_USERNAME, username)
        editor.apply()
    }

    // Id

    override fun setCurrentId(id: Int?) {
        val editor = sharedPreferences.edit()
        if (id == null)
            editor.remove(PREF_CURRENT_ACCOUNT_ID)
        else
            editor.putInt(PREF_CURRENT_ACCOUNT_ID, id)
        editor.apply()
    }

    override fun getCurrentId(): Int = sharedPreferences.getInt(PREF_CURRENT_ACCOUNT_ID, -1)

    // Role

    override fun setCurrentRole(role: String?) {
        val editor = sharedPreferences.edit()
        if (role == null)
            editor.remove(PREF_CURRENT_ACCOUNT_ROLE)
        else
            editor.putString(PREF_CURRENT_ACCOUNT_ROLE, role)
        editor.apply()
    }

    override fun getCurrentRole(): String? = sharedPreferences.getString(PREF_CURRENT_ACCOUNT_ROLE, null)


    companion object {
        private const val PREF_CURRENT_ACCOUNT_TOKEN = "currentToken"
        private const val PREF_CURRENT_ACCOUNT_USERNAME = "currentUsername"
        private const val PREF_CURRENT_ACCOUNT_ROLE = "currentRole"
        private const val PREF_CURRENT_ACCOUNT_ID = ""
    }

}