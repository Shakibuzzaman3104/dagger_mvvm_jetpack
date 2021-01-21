package com.example.dagger_first

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.dagger_first.ui.auth.AuthActivity
import com.example.dagger_first.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    private companion object val TAG="BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        sessionManager.getAuthUser().observe(this) {resource->
            if (resource != null) {
                when (resource.status) {
                    AuthResource.AuthStatus.LOADING -> {

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {

                        Log.d("Success", "" + resource.data?.email)
                    }
                    AuthResource.AuthStatus.ERROR -> {

                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        Log.d(TAG,"Not Authenticated")
                        navLoginScreen()
                    }
                }
            }
        }
    }

    private fun navLoginScreen(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

}