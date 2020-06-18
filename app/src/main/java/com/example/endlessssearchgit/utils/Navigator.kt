package com.example.endlessssearchgit.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class Navigator() {
    private lateinit var mContext: Context

    var mFragmentContainerId: Int? = null

    constructor(context: Context, id: Int) : this() {
        this.mContext = context
        this.mFragmentContainerId = id
    }

    fun showDefaultFragment(defaultFragment: Fragment) {
        mFragmentContainerId?.let { container ->
            val fragment = getFragmentManager().findFragmentById(container)
            if (fragment == null) {
                val fragmentTransaction = getFragmentManager().beginTransaction()
                fragmentTransaction.replace(container, defaultFragment)
                fragmentTransaction.commit()
            }
        }
    }

    private fun getFragmentManager(): FragmentManager {
        if (mContext is FragmentActivity) {
            return (mContext as FragmentActivity).supportFragmentManager
        }
        throw IllegalStateException("The context being used is not an instance of FragmentActivity.")
    }
}