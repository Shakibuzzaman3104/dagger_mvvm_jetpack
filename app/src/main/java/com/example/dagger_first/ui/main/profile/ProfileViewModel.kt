package com.example.dagger_first.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_first.SessionManager
import com.example.dagger_first.models.ModelUser
import com.example.dagger_first.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) :
    ViewModel() {
    init {
        Log.d("ProfileViewModel", "Working")
    }


    fun getAuthenticatedUser(): LiveData<AuthResource<ModelUser>> {
        return sessionManager.getAuthUser()
    }
}