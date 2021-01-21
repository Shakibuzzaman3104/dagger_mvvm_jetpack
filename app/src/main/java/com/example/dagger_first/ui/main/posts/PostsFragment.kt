package com.example.dagger_first.ui.main.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_first.R
import com.example.dagger_first.databinding.FragmentPostsBinding
import com.example.dagger_first.databinding.FragmentProfileBinding
import com.example.dagger_first.ui.main.profile.ProfileViewModel
import com.example.dagger_first.utils.VerticalSpacingItemDecoration
import com.example.dagger_first.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class PostsFragment : DaggerFragment() {

    companion object val TAG = "PostsFragment"

    lateinit var postsBinding: FragmentPostsBinding

    lateinit var postsViewModel: PostsViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postsBinding = FragmentPostsBinding.inflate(inflater, container, false)

        initViewModel()
        subscribeObservers()
        initRecyclerView()

        return postsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun initViewModel() {
        postsViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(PostsViewModel::class.java)
    }

    private fun subscribeObservers() {
        postsViewModel.getPosts().removeObservers(viewLifecycleOwner)
        postsViewModel.getPosts().observe(viewLifecycleOwner) {

            if (it != null) {
                when (it.status) {
                    PostsResource.Status.SUCCESS -> {
                        postsBinding.progressBar.visibility = View.GONE
                        postsBinding.errorMessage.visibility = View.GONE
                        postsRecyclerAdapter.setPosts(it.data!!)
                    }
                    PostsResource.Status.ERROR -> {
                        postsBinding.progressBar.visibility = View.GONE
                        postsBinding.errorMessage.visibility =View.VISIBLE
                    }
                    PostsResource.Status.LOADING -> {
                        postsBinding.progressBar.visibility = View.VISIBLE
                        postsBinding.errorMessage.visibility =View.GONE
                    }
                }
            }

        }
    }

    private fun initRecyclerView() {
        postsBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        postsBinding.recyclerView.addItemDecoration(VerticalSpacingItemDecoration(15))
        postsBinding.recyclerView.adapter = postsRecyclerAdapter
    }


}