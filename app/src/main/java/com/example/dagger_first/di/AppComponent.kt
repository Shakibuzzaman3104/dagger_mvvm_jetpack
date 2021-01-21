package com.example.dagger_first.di

import android.app.Application
import com.example.dagger_first.BaseApplication
import com.example.dagger_first.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,ActivityBuildersModule::class,AppModule::class,ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager():SessionManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}