package com.example.dagger_first.ui.main.profile

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.dagger_first.R
import com.example.dagger_first.databinding.FragmentProfileBinding
import com.example.dagger_first.models.ModelUser
import com.example.dagger_first.ui.auth.AuthResource
import com.example.dagger_first.ui.auth.AuthResource.AuthStatus.*
import com.example.dagger_first.ui.auth.AuthViewModel
import com.example.dagger_first.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    lateinit var profileBinding: FragmentProfileBinding

    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)

        Toast.makeText(context, "ProfileFragment", Toast.LENGTH_SHORT).show()
        initViewModel()
        subscribeObservers()
        return profileBinding.root
    }

    private fun subscribeObservers() {
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner) { data ->
            if (data != null) {
                when (data.status) {
                    AUTHENTICATED -> setUserDetails(data.data)
                    ERROR -> setErrorDetails(data.message)

                }
            }
        }
    }

    private fun setErrorDetails(message: String?) {
        profileBinding.email.text = message
        profileBinding.username.text = "Error"
        profileBinding.website.text = "Error"
    }

    private fun setUserDetails(user: ModelUser?) {
        profileBinding.email.text = user?.email
        profileBinding.username.text = user?.username
        profileBinding.website.text = user?.website
    }


    private fun initViewModel() {
        profileViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
    }


}