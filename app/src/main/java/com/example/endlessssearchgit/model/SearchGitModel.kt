package com.example.endlessssearchgit.model

data class SearchGitData(
    val items: ArrayList<Items>
)

data class Items(
    val login: String,
    val avatar_url: String
)