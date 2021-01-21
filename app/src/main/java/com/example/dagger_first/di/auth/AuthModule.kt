package com.example.dagger_first.di.auth

import com.example.dagger_first.SessionManager
import com.example.dagger_first.di.repository.AuthRepository
import com.example.dagger_first.network.auth.AuthApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
/*    @Provides
    fun provideAuthRepository(authApi: AuthApi,sessionManager: SessionManager):AuthRepository{
        return AuthRepository(authApi,sessionManager)
    }*/

}