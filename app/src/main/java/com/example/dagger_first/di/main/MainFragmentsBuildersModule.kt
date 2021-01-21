package com.example.dagger_first.di.main

import com.example.dagger_first.ui.main.posts.PostsFragment
import com.example.dagger_first.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector()
    abstract fun contributePostsFragment(): PostsFragment


}