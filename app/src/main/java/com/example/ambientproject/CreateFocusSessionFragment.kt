package com.example.ambientproject

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.ambientproject.databinding.CreateFocusSessionFragmentBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import edu.cs371m.reddit.ui.CreateFocusSessionRowAdapter
import kotlin.random.Random

class CreateFocusSessionFragment : Fragment() {
    private fun setDisplayHomeAsUpEnabled(value : Boolean) {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    companion object {
        private const val soundsFragmentTag = "soundsFragmentTag"
        private const val sessionsFragmentTag = "sessionsFragmentTag"
        private const val createFocusSessionFragTag = "createFocusSessionFragTag"
        fun newInstance(): CreateFocusSessionFragment {
            return CreateFocusSessionFragment()
        }
    }

    private var _binding: CreateFocusSessionFragmentBinding? = null
    private val binding get() = _binding!!
    private val labSoundViewModel: LabSoundViewModel by activityViewModels()
    private val focusSessionModel: FocusSessionViewModel by activityViewModels()
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
        res -> onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Log.i("XXX", "user has successfull signed in: $user")
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
        _binding = CreateFocusSessionFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDisplayHomeAsUpEnabled(true)

        val rv = binding.recyclerView
        val adapter = CreateFocusSessionRowAdapter(labSoundViewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = null
        labSoundViewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            selectedList ->
            adapter.submitList(selectedList)
            adapter.notifyDataSetChanged()
        }

        binding.root.isClickable = true

        // Check sign in
        val user = FirebaseAuth.getInstance().currentUser
        Log.i("XXX", "user: $user")
        if (user == null) {
            binding.createFocusSessionButton.text = "Log in to Create Focus Session"
        }

        binding.createFocusSessionButton.setOnClickListener {
            if (user == null) {
                // Choose authentication providers
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build())

                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build()
                signInLauncher.launch(signInIntent)
            }

            if (binding.sessionTitleEditText.text.toString().isNotEmpty()) {
                labSoundViewModel.getTurnedOnAmbientItemList()
                    .observe(viewLifecycleOwner) { selectedList ->
                        val focusSession = FocusSession(
                            Random.nextInt(0, 10000).toString(),
                            binding.sessionTitleEditText.text.toString(),
                            binding.sessionDescriptionEditText.text.toString(),
                            selectedList.map { item -> item.rawSongId })
                        focusSessionModel.insertFocusSession(focusSession)
                        activity?.supportFragmentManager?.findFragmentByTag(
                            createFocusSessionFragTag
                        )
                            ?.let { it1 ->
                                activity?.supportFragmentManager?.beginTransaction()
                                    ?.remove(it1)
                                    ?.commit()
                            }
                        findNavController().navigate(R.id.navigation_session)
                    }
            }
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Menu is already inflated by main activity
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                activity?.supportFragmentManager?.findFragmentByTag(
                    createFocusSessionFragTag
                )
                    ?.let { it1 ->
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.remove(it1)
                            ?.commit()
                    }
                findNavController().navigate(R.id.navigation_sounds)
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onDestroyView() {
        Log.i("XXX", "CreateFocusSession onDestroyView")
        // XXX Write me
        // Don't let back to home button stay when we exit favorites
        super.onDestroyView()
        setDisplayHomeAsUpEnabled(false)
    }
}