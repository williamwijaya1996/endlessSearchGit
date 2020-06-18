package com.example.endlessssearchgit.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class CheckConnectivityReceiver : BroadcastReceiver() {

    companion object {
        lateinit var checkConnectivityListener: CheckConnectivityListener
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activityNetwork = cm.activeNetworkInfo

        val isConnected = activityNetwork != null && activityNetwork.isConnected

        if (checkConnectivityListener != null) {
            checkConnectivityListener.onNetworkConnectionChanged(isConnected)
        }
    }

    open interface CheckConnectivityListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }
}