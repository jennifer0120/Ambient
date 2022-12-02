package com.example.ambientproject

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class LabSoundsFragment: Fragment() {
    companion object {
        val TAG: String = LabSoundsFragment::class.java.simpleName
        private const val createFocusSessionFragTag = "createFocusSessionFragTag"
        private const val sessionsFragment = "sessionsFragmentTag"

        fun newInstance(): LabSoundsFragment {
            return LabSoundsFragment()
        }
        private val playerMap: HashMap<String, MediaPlayer?> = HashMap()

    }

    private var _binding: RecyclerMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LabSoundViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LabSoundAdapter(viewModel)
        binding.rainRecyclerView.layoutManager = GridLayoutManager(binding.rainRecyclerView.context, 2)
        binding.rainRecyclerView.adapter = adapter

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            binding.createSession.text = "Log in first before creating a session"
        }
        viewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            itemList ->
            if (itemList.isEmpty()) {
                binding.createSession.setBackgroundColor(Color.parseColor("#a9def9"))
                binding.createSession.setTextColor(Color.parseColor("#AA1363DF"))
            } else {
                binding.createSession.setBackgroundColor(Color.parseColor("#fcf6bd"))
                binding.createSession.setTextColor(Color.parseColor("#fca311"))
            }

            binding.createSession.setOnClickListener {
                if (itemList.isNotEmpty() && user != null) {
                    // Instead of creating an activity here, create a fragment instead because the viewModel is tied to the activity lifecycle
                    requireActivity().supportFragmentManager.commit {
                        replace(R.id.nav_host_fragment_activity_main, CreateFocusSessionFragment.newInstance(), createFocusSessionFragTag)
                        addToBackStack(createFocusSessionFragTag)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearOutTurnedOnAmbientItemList()
        _binding = null
    }
}