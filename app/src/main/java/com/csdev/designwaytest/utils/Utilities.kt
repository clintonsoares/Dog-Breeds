package com.csdev.designwaytest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.TextUtils
import java.util.regex.Pattern

object Utilities {

    fun isValidPassword(target: String?): Boolean {
        return target?.let {
            val passwordREGEX = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
//                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[!@£#$%^&*+=_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");
            !TextUtils.isEmpty(it) && passwordREGEX.matcher(it).matches()
        } ?: false
    }

    fun isValidFullName(fullName: String): Boolean {
        // Split the full name into parts by whitespace
        val nameParts = fullName.split(" ")

        // Check if there are at least two parts (first name and last name)
        return nameParts.size >= 2
    }

    // check if internet is available to the device
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}