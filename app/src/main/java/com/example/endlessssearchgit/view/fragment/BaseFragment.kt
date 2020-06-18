package com.example.endlessssearchgit.view.fragment

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.endlessssearchgit.view.BaseActivity

open class BaseFragment : Fragment() {


    protected fun closeKeyboard() {
        val view = getBaseActivity()?.currentFocus
        if (view != null) {
            val imm =
                view!!.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected fun getBaseActivity(): BaseActivity? {
        return if (activity is BaseActivity) {
            activity as BaseActivity?
        } else {
            null
        }
    }
}