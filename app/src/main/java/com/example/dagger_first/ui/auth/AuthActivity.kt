package com.example.dagger_first.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.dagger_first.databinding.ActivityAuthBinding
import com.example.dagger_first.ui.main.MainActivity
import com.example.dagger_first.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.math.log2

class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setLogo()
        subscribeObservers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AuthViewModel::class.java)
    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this) { resource ->
            if (resource != null) {
                when (resource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showHideProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showHideProgressBar(false)
                        Log.d("Success", "" + resource.data?.email)
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showHideProgressBar(false)
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showHideProgressBar(false)
                    }
                }
            }
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showHideProgressBar(boolean: Boolean) {
        if (boolean) binding.progressBar.visibility =
            View.VISIBLE else binding.progressBar.visibility = View.GONE
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(binding.loginLogo)
    }

    fun loginCLick(view: View) {

        if (binding.userIdInput.text!!.trim().isEmpty()) {
            return
        }
        viewModel.authUser(
            Integer.parseInt(binding.userIdInput.text.toString())
        )
    }
}