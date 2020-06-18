package com.example.endlessssearchgit.request

import android.app.Activity
import com.example.endlessssearchgit.model.SearchGitData
import com.example.endlessssearchgit.network.BaseNetworkRequest
import com.example.endlessssearchgit.network.RetrofitProvider
import retrofit2.Call
import kotlin.properties.Delegates

open class GetSearchDataGitListRequest() : BaseNetworkRequest<SearchGitData>() {

    private var q: String? = null
    private var perPage by Delegates.notNull<Int>()
    private var page by Delegates.notNull<Int>()


    override fun getCall(): Call<SearchGitData> {
        return RetrofitProvider.getGitResource().getGitUserList(q, perPage, page)
    }

    constructor(activity: Activity, q: String?, perPage: Int, page: Int) : this() {
        super.BaseNetworkRequest(activity)
        this.q = q
        this.perPage = perPage
        this.page = page

    }

}