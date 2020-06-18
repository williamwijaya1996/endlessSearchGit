package com.example.endlessssearchgit.view

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.endlessssearchgit.R
import com.example.endlessssearchgit.network.CheckConnectivityReceiver
import com.example.endlessssearchgit.utils.Navigator
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(),
    CheckConnectivityReceiver.CheckConnectivityListener {

    lateinit var mNavigator: Navigator
    lateinit var mContext: Context


    protected fun getDefaultContainerId() {
        getNavigator().showDefaultFragment(setDefaultFragment())
    }

    private fun getNavigator(): Navigator {
        return mNavigator
    }

    fun showActionToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    protected fun setConnectivityStateChangeReceiver() {
        val intentFilter = IntentFilter()
        setConnectivityListener(getConnectivityStateChangeListener())
        intentFilter.addAction(ConnectivityManager.EXTRA_CAPTIVE_PORTAL)
        val checkConnectivityReceiver = CheckConnectivityReceiver()
        registerReceiver(checkConnectivityReceiver, intentFilter)
    }

    private fun setConnectivityListener(listener: CheckConnectivityReceiver.CheckConnectivityListener) {
        CheckConnectivityReceiver.checkConnectivityListener = listener
    }

    private fun showSnackBar(isConnected: Boolean) {
        var message: String?
        if (isConnected) {
            message = resources.getString(R.string.connected_message)
        } else {
            message = resources.getString(R.string.disconnect_message)
        }
        val snackBar = Snackbar.make(setView(), message, Snackbar.LENGTH_LONG)
        snackBar.show()
    }

    abstract fun setDefaultFragment(): Fragment

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnackBar(isConnected)
    }

    abstract fun setView(): View
    abstract fun getConnectivityStateChangeListener(): CheckConnectivityReceiver.CheckConnectivityListener
}