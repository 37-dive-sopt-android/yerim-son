package com.sopt.dive.util

import android.content.Context
import android.content.SharedPreferences

data class UserInfo(
    val username: String?,
    val pw: String?,
    val name: String?,
    val email: String?,
    val age: String?
)

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_PW = "pw"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_AGE = "age"
    }

    fun registerUser(username: String, pw: String, name: String, email: String, age: String) {
        prefs.edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PW, pw)
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putString(KEY_AGE, age)
            .apply()
    }

    fun isUserValid(id: String, pw: String): Boolean {
        val savedId = prefs.getString(KEY_USERNAME, null)
        val savedPw = prefs.getString(KEY_PW, null)
        return savedId == id && savedPw == pw
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(
            username = prefs.getString(KEY_USERNAME, null),
            pw = prefs.getString(KEY_PW, null),
            name = prefs.getString(KEY_NAME, null),
            email = prefs.getString(KEY_EMAIL, null),
            age = prefs.getString(KEY_AGE, null),
        )
    }

}