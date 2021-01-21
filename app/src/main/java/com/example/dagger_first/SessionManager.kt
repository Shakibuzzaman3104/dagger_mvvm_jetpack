package com.example.dagger_first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger_first.models.ModelUser
import com.example.dagger_first.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    companion object

    val TAG = "SessionManager"
    private val cachedUser: MediatorLiveData<AuthResource<ModelUser>> = MediatorLiveData()


    fun authenticateWithId(source: LiveData<AuthResource<out ModelUser>>) {
        if (cachedUser != null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source) { auth ->
                cachedUser.value = auth as AuthResource<ModelUser>?
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut()
    {
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser():LiveData<AuthResource<ModelUser>>
    {
        return cachedUser
    }

}