package com.example.ambientproject

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding
import com.example.ambientproject.databinding.SettingsRecyclerMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SettingsFragment: Fragment() {
    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
    }

    private var _binding: SettingsRecyclerMainBinding? = null
    private val binding get() = _binding!!
    private var user = FirebaseAuth.getInstance().currentUser
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
        result -> onSignInResult(result)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        Log.i("XXX", "response: $response")
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            user = FirebaseAuth.getInstance().currentUser
            // TODO: If the user doesn't exist then create one.
//            usersRepository.createUser(user)
            setProfile(user)
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsRecyclerMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setProfile(user)

        binding.button.setOnClickListener {
            // if user is null, then start the sign in process
            user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                // Login
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build())

                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build()
                signInLauncher.launch(signInIntent)
            } else {
                FirebaseAuth.getInstance().signOut()
                setProfile(null)
            }
        }


        return root
    }

    private fun setProfile(user: FirebaseUser?) {
        if (user == null) {
            binding.userName.text = ""
            binding.button.text = "Log In"
        } else {
            binding.userName.text = user.displayName
            binding.button.text = "Log Out"
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}