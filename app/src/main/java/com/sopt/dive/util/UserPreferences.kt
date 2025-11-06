package com.sopt.dive.util

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val KEY_ID = "id"
        private const val KEY_PW = "pw"
        private const val KEY_NICKNAME = "nickname"
        private const val KEY_MBTI = "mbti"
    }

    fun registerUser(id: String, pw: String, nickname: String, mbti: String) {
        prefs.edit()
            .putString(KEY_ID, id)
            .putString(KEY_PW, pw)
            .putString(KEY_NICKNAME, nickname)
            .putString(KEY_MBTI, mbti)
            .apply()
    }

    fun isUserValid(id: String, pw: String): Boolean {
        val savedId = prefs.getString(KEY_ID, null)
        val savedPw = prefs.getString(KEY_PW, null)
        return savedId == id && savedPw == pw
    }

}