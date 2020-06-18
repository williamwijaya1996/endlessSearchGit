package com.example.endlessssearchgit.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.endlessssearchgit.R
import com.example.endlessssearchgit.databinding.ItemListBinding
import com.example.endlessssearchgit.model.Items

class SearchGitListAdapter(val searchGitList: ArrayList<Items>) :
    RecyclerView.Adapter<SearchGitListAdapter.SearchGitHolder>() {

    var isShowShimmer: Boolean = false
    private val shimmerItems = 10

    fun updateDataList(gitList: List<Items>, isShowShimmer: Boolean) {
        searchGitList.addAll(gitList)
        this.isShowShimmer = isShowShimmer
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGitHolder {
        val view = DataBindingUtil.inflate<ItemListBinding>(
            LayoutInflater.from(parent.context), R.layout.item_list, parent, false
        )
        return SearchGitHolder(view)
    }

    override fun getItemCount(): Int = if (isShowShimmer) {
        shimmerItems
    } else {
        searchGitList.size
    }


    override fun onBindViewHolder(holder: SearchGitHolder, position: Int) {
        val shimmer = holder.view.shimmerLayout
        val llShimmer = holder.view.llShimmer
        val llNotShimmer = holder.view.llNotShimmer
        if (isShowShimmer) {
            llShimmer.visibility = View.VISIBLE
            llNotShimmer.visibility = View.GONE
            shimmer.setShimmerAnimationDuration(1500)
            shimmer.startShimmerAnimation()
        } else {
            shimmer.stopShimmerAnimation()
            llShimmer.visibility = View.GONE
            llNotShimmer.visibility = View.VISIBLE
            holder.view.git = searchGitList[position]
        }

    }

    class SearchGitHolder(var view: ItemListBinding) : RecyclerView.ViewHolder(view.root)
}