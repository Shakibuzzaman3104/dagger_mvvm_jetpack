package com.example.dagger_first.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_first.di.repository.AuthRepository
import com.example.dagger_first.models.ModelUser



import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun authUser(id:Int){
        authRepository.authenticateWithId(id)
    }

    fun observeUser():LiveData<AuthResource<ModelUser>>{
        return authRepository.observeUser()
    }


}