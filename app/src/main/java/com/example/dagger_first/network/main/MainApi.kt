package com.example.dagger_first.network.main

import com.example.dagger_first.models.ModelPost
import com.example.dagger_first.models.ModelUser
import com.example.dagger_first.ui.main.posts.PostsResource
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPosts(@Query("userId") id: Int): Flowable<List<ModelPost>>
}