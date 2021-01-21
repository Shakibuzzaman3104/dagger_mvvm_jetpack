package com.example.dagger_first.di

import com.example.dagger_first.di.auth.AuthModule
import com.example.dagger_first.di.auth.AuthScope
import com.example.dagger_first.di.auth.AuthViewModelsModule
import com.example.dagger_first.di.main.MainFragmentsBuildersModule
import com.example.dagger_first.di.main.MainModule
import com.example.dagger_first.di.main.MainScope
import com.example.dagger_first.di.main.MainViewModelsModule
import com.example.dagger_first.ui.auth.AuthActivity
import com.example.dagger_first.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentsBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}