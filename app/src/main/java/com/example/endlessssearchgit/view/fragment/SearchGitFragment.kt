package com.example.endlessssearchgit.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.endlessssearchgit.R
import com.example.endlessssearchgit.view.MainActivity
import com.example.endlessssearchgit.view.adapter.SearchGitListAdapter
import com.example.endlessssearchgit.viewmodel.SearchGitViewModel
import kotlinx.android.synthetic.main.fragment_search_git.*

/**
 * A simple [Fragment] subclass.
 */
class SearchGitFragment : BaseFragment() {

    private lateinit var viewModel: SearchGitViewModel
    private val searchGitListAdapter = SearchGitListAdapter(arrayListOf())
    private var page = 1
    var query: String = ""
    val perPage: Int = 10
    private var isBoolGetData: Boolean = true
    private var isShowShimmer = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_git, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchGitViewModel::class.java)
        viewModel.initial(activity as MainActivity)
        setValue()
        observeViewModel()
    }

    private fun setValue() {
        rv_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchGitListAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                    val totalItemCount = (layoutManager as LinearLayoutManager).itemCount
                    val firstVisibleItemPosition =
                        (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >=
                        totalItemCount && page != 51 && query != ""
                    ) {
                        isShowShimmer = false
                        isBoolGetData = true
                        viewModel.fetchSearch(query, perPage, page)
                        ll_bottom_progressbar_list.visibility = View.VISIBLE
                    }
                }
            })
        }

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                query = et_search.text.toString()
                if (actionId == EditorInfo.IME_ACTION_DONE && query != "") {
                    isBoolGetData = true
                    closeKeyboard()
                    page = 1
                    viewModel.fetchSearch(query, perPage, page)
                    searchGitListAdapter.searchGitList.clear()
                    isShowShimmer = true
                    searchGitListAdapter.isShowShimmer = isShowShimmer
                    return true
                }
                return false
            }
        })
    }


    private fun observeViewModel() {
        viewModel.gitDataList.observe(this, Observer { data ->
            data?.let {
                page++
                isShowShimmer = false
                ll_bottom_progressbar_list.visibility = View.GONE
                if (isBoolGetData) {
                    searchGitListAdapter.updateDataList(data.items, isShowShimmer)
                }
                isBoolGetData = false
            }
        })
        viewModel.isGitFail.observe(this, Observer {
            it?.let {
                if (it) {
                    if (isBoolGetData) {
                        searchGitListAdapter.updateDataList(arrayListOf(), false)
                        ll_bottom_progressbar_list.visibility = View.GONE
                        getBaseActivity()?.showActionToast(resources.getString(R.string.please_wait))
                    }
                    isBoolGetData = false
                }
            }
        })
    }

}
