package com.example.endlessssearchgit.network

import com.example.endlessssearchgit.model.SearchGitData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitService {

    @GET("search/users")
    fun getGitUserList(
        @Query("q") q: String?,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Call<SearchGitData>
}