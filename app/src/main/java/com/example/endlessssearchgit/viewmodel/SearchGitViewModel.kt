package com.example.endlessssearchgit.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.endlessssearchgit.model.SearchGitData
import com.example.endlessssearchgit.request.GetSearchDataGitListRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchGitViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var activity: Activity
    val gitDataList = MutableLiveData<SearchGitData>()
    val isGitFail = MutableLiveData<Boolean>()

    fun initial(activity: Activity) {
        this.activity = activity
    }

    fun fetchSearch(q: String?, perPage: Int, page: Int) {
        launch {
            val getSearchDataGitListRequest =
                object : GetSearchDataGitListRequest(activity, q, perPage, page) {
                    override fun onSuccess(response: Response<SearchGitData>) {
                        super.onSuccess(response)
                        gitDataList.value = response.body()
                    }

                    override fun onFailed(response: Response<SearchGitData>) {
                        super.onFailed(response)
                        isGitFail.value = true
                    }
                }
            getSearchDataGitListRequest.execute()
        }
    }
}