package com.example.dagger_first.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_first.SessionManager
import com.example.dagger_first.di.repository.PostsRepository
import com.example.dagger_first.models.ModelPost
import javax.inject.Inject


class PostsViewModel @Inject constructor(private val postsRepository: PostsRepository) :
    ViewModel() {


    fun getPosts(): LiveData<PostsResource<List<ModelPost>>> {
        return postsRepository.getPosts()
    }

}