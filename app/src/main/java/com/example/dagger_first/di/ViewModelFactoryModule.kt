package com.example.dagger_first.di

import androidx.lifecycle.ViewModelProvider
import com.example.dagger_first.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory
}