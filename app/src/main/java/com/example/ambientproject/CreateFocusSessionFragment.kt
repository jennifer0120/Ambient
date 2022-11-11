package com.example.ambientproject

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.example.ambientproject.databinding.CreateFocusSessionFragmentBinding
import edu.cs371m.reddit.ui.CreateFocusSessionRowAdapter

class CreateFocusSessionFragment : Fragment() {
    private fun setDisplayHomeAsUpEnabled(value : Boolean) {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    companion object {
        private const val soundsFragmentTag = "soundsFragmentTag"
        fun newInstance(): CreateFocusSessionFragment {
            return CreateFocusSessionFragment()
        }
    }

    private var _binding: CreateFocusSessionFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LabSoundViewModel by activityViewModels()

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
        val adapter = CreateFocusSessionRowAdapter(viewModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = null
        viewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            selectedList ->
            adapter.submitList(selectedList)
            adapter.notifyDataSetChanged()
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Menu is already inflated by main activity
            }
            // XXX Write me, onMenuItemSelected
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                activity?.supportFragmentManager?.popBackStack()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, LabSoundsFragment.newInstance(), soundsFragmentTag)
                    ?.addToBackStack(null)
                    ?.commit()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onDestroyView() {
        // XXX Write me
        // Don't let back to home button stay when we exit favorites
        setDisplayHomeAsUpEnabled(false)
        super.onDestroyView()
    }
}