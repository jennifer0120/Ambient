package com.example.ambientproject

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding


class SoundsFragment: Fragment() {
    companion object {
        val TAG: String = SoundsFragment::class.java.simpleName
        private const val createFocusSessionFragTag = "createFocusSessionFragTag"
        fun newInstance(): SoundsFragment {
            return SoundsFragment()
        }
    }

    private var _binding: RecyclerMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

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
        binding.rainRecyclerView.layoutManager = GridLayoutManager(binding.rainRecyclerView.context, 2)
        binding.rainRecyclerView.adapter = SoundsAdapter(viewModel)

        val turnedOnAmbientItemList = viewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            itemList ->
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
                            replace(com.example.ambientproject.R.id.nav_host_fragment_activity_main, CreateFocusSessionFragment.newInstance(), createFocusSessionFragTag)
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