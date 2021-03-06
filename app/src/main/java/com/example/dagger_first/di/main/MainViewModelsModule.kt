package com.example.dagger_first.di.main

import androidx.lifecycle.ViewModel
import com.example.dagger_first.di.ViewModelKey
import com.example.dagger_first.ui.auth.AuthViewModel
import com.example.dagger_first.ui.main.posts.PostsViewModel
import com.example.dagger_first.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

        @Binds
        @IntoMap
        @ViewModelKey(ProfileViewModel::class)
        abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel):ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(PostsViewModel::class)
        abstract fun bindPostsViewModel(postsViewModel: PostsViewModel):ViewModel

}