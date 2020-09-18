package com.divinkas.app.githubfinder.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.Fragment
import com.divinkas.app.githubfinder.R

fun isInternetAvailable(context: Context): Boolean {
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
        return getNetworkCapabilities(activeNetwork)?.run {
            when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }
}

fun Fragment.runWithNetworkConnection(action: () -> Unit = {}) {
    if (isInternetAvailable(context!!)) {
        action()
    } else {
        toast(R.string.you_are_offline)
    }
}