package com.example.dagger_first.di.main

import com.example.dagger_first.SessionManager
import com.example.dagger_first.di.repository.PostsRepository
import com.example.dagger_first.network.main.MainApi
import com.example.dagger_first.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    @MainScope
    @Provides
    fun providePostsApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

/*
    @Provides
    fun providePostsRepos(sessionManager: SessionManager, mainApi: MainApi): PostsRepository {
        return PostsRepository(sessionManager = sessionManager, mainApi = mainApi)
    }
*/

    @MainScope
    @Provides
    fun providePostsRecyclerAdapter(): PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

}