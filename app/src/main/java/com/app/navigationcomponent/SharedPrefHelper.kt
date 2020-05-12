package com.app.navigationcomponent

import android.content.Context
import android.content.SharedPreferences


class SharedPrefHelper(context: Context) {

    private val PREF_NAME = "MY_PREF"
    private val isUserLoggedIn:String = "User Logged In"
    private val userExperience: String = "User Experience"
    var mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun setBooleanValue(key: String, value: Boolean) {
        mPref.edit()
            .putBoolean(key, value)
            .apply()
    }

    private fun getBooleanValue(key: String): Boolean {
        return mPref.getBoolean(key, false)
    }

    fun getUserLoggedInState():Boolean{
        return getBooleanValue(isUserLoggedIn)
    }

    fun setUserLoggedInState(isUserLoggerIn: Boolean){
        setBooleanValue(isUserLoggedIn, isUserLoggerIn)
    }

    fun setUserExperience(isRegularExperience: Boolean){
        setBooleanValue(userExperience, isRegularExperience)
    }

    fun getUserExperience():Boolean{
        return getBooleanValue(userExperience)
    }


}