package com.example.endlessssearchgit.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.endlessssearchgit.R
import com.example.endlessssearchgit.network.CheckConnectivityReceiver
import com.example.endlessssearchgit.view.fragment.SearchGitFragment
import com.example.endlessssearchgit.utils.Navigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this
        mNavigator = Navigator(
            this,
            R.id.main_activity_fragment
        )
        getDefaultContainerId()
        setConnectivityStateChangeReceiver()
    }

    override fun setDefaultFragment(): Fragment {
        return SearchGitFragment()
    }

    override fun setView(): View {
        return root_layout
    }

    override fun getConnectivityStateChangeListener(): CheckConnectivityReceiver.CheckConnectivityListener {
        return this
    }
}
