package com.example.ambientproject

import android.app.Activity.RESULT_OK
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.loader.content.AsyncTaskLoader
import coil.load
import com.example.ambientproject.databinding.SettingsRecyclerMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL


class SettingsFragment: Fragment() {
    companion object {
        val TAG: String = SettingsFragment::class.java.simpleName
    }

    private var _binding: SettingsRecyclerMainBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private var user = FirebaseAuth.getInstance().currentUser
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
        result -> onSignInResult(result)
    }

    private fun getProfileUrl(user: FirebaseUser): String {
        val displayName = user.displayName
        if (displayName != null) {
            return "https://robohash.org/$displayName.png?set=set4"
        }
        return "https://robohash.org/${user.uid}.png?set=set4"
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            user = FirebaseAuth.getInstance().currentUser
            // TODO: If the user doesn't exist then create one.
            val userData = User(user!!.uid, user!!.displayName, getProfileUrl(user!!))
            userViewModel.insertUser(userData)
            setProfile(userData)
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

        user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val job = Job()
            val uiScope = CoroutineScope(Dispatchers.Main + job)
            uiScope.launch {
                val userData = userViewModel.getUser(user!!.uid)
                setProfile(userData)
            }
        }

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

    private fun setProfile(user: User?) {
        if (user == null) {
            binding.userName.text = ""
            binding.button.text = "Log In"
            binding.profilePic.visibility = View.INVISIBLE
        } else {
            binding.userName.text = user.displayName
            binding.button.text = "Log Out"
            binding.profilePic.visibility = View.VISIBLE
            binding.profilePic.load(user.profileUrl)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}