package com.example.dagger_first.di.auth

import androidx.lifecycle.ViewModel
import com.example.dagger_first.di.ViewModelKey
import com.example.dagger_first.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

        @Binds
        @IntoMap
        @ViewModelKey(AuthViewModel::class)
        abstract fun bindAuthViewModel(authViewModel: AuthViewModel):ViewModel

}