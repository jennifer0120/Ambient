package com.example.ambientproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding


class LabSoundsFragment: Fragment() {
    companion object {
        val TAG: String = LabSoundsFragment::class.java.simpleName
        private const val createFocusSessionFragTag = "createFocusSessionFragTag"
        private const val sessionsFragment = "sessionsFragmentTag"
        fun newInstance(): LabSoundsFragment {
            return LabSoundsFragment()
        }
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
        Log.i("XXX", "LabsoundFragment OnViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val adapter = LabSoundAdapter(viewModel)
        binding.rainRecyclerView.layoutManager = GridLayoutManager(binding.rainRecyclerView.context, 2)
        binding.rainRecyclerView.adapter = adapter

        viewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            itemList ->
            Log.i("XXX", "itemList: ${itemList.size}")
            if (itemList.isEmpty()) {
                binding.createSession.setBackgroundColor(Color.parseColor("#EFEFEF"))
                binding.createSession.setTextColor(Color.parseColor("#000000"))
            } else {
                binding.createSession.setBackgroundColor(Color.parseColor("#FF0000"))
                binding.createSession.setTextColor(Color.parseColor("#FFFFFF"))
            }

            binding.createSession.setOnClickListener {
                if (itemList.isNotEmpty()) {
                    // Instead of creating an activity here, create a fragment instead because the viewModel is tied to the activity lifecycle
                    if (requireActivity().supportFragmentManager.findFragmentByTag(createFocusSessionFragTag) == null) {
                        requireActivity().supportFragmentManager.commit {
                            replace(R.id.nav_host_fragment_activity_main, CreateFocusSessionFragment.newInstance(), createFocusSessionFragTag)
                            addToBackStack(createFocusSessionFragTag)
                            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}