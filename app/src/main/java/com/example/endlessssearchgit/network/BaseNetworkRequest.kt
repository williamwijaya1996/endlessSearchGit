package com.example.endlessssearchgit.network

import android.app.Activity
import com.example.endlessssearchgit.view.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseNetworkRequest<T> {

    private lateinit var mBaseActivity: BaseActivity

    fun BaseNetworkRequest(activity: Activity) {
        if (activity is BaseActivity) {
            mBaseActivity = activity
            mBaseActivity.mContext = activity
        }
    }

    fun execute() {
        if (getCall() == null) {
            return
        }

        getCall().enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                if (code == 200) {
                    onSuccess(response)
                } else {
                    onFailed(response)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t is SocketTimeoutException) {
                    onFailConnection(t)
                } else if (t is IOException || t is UnknownHostException) {
                    onIOException(t)
                } else {
                    onError(t)
                }
            }
        })
    }

    abstract fun getCall(): Call<T>
    protected open fun onSuccess(response: Response<T>) {}
    protected open fun onFailed(response: Response<T>) {}
    protected open fun onError(t: Throwable) {}
    protected open fun onFailConnection(t: Throwable) {
        mBaseActivity.showActionToast(t.toString())
    }

    protected open fun onIOException(t: Throwable) {
        mBaseActivity.showActionToast(t.toString())
    }
}