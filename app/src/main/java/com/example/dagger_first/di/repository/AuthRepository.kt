package com.example.dagger_first.di.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.dagger_first.SessionManager
import com.example.dagger_first.models.ModelUser
import com.example.dagger_first.network.auth.AuthApi
import com.example.dagger_first.ui.auth.AuthResource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) {


    fun authenticateWithId(id: Int) {
        sessionManager.authenticateWithId(queryUserId(id))
    }


   private fun queryUserId(id: Int): LiveData<AuthResource<out ModelUser>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(id)
                .onErrorReturn {
                    val user = ModelUser()
                    user.id = -1
                    user
                }.map { user ->
                    if (user.id == -1) AuthResource.error(
                        "Couldn't Authenticate",
                        null
                    ) else AuthResource.authenticated(user)
                }
                .subscribeOn(Schedulers.io())
        )
    }


    fun observeUser(): LiveData<AuthResource<ModelUser>> {
        return sessionManager.getAuthUser()
    }


}





