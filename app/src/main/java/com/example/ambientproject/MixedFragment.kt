package com.example.ambientproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding

class MixedFragment : Fragment() {
    companion object {
        val TAG: String = MixedFragment::class.java.simpleName
    }

    // https://developer.android.com/topic/libraries/view-binding#fragments
    private var _binding: RecyclerMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        Log.d(TAG, "onCreateView ${viewModel.selected}")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SSS
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Simple Album List"
//        binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context)
//        binding.recyclerView.adapter = SimpleAdapter(viewModel)
        //EEE // XXX Write me, action bar title,
        // recycler view layout manager, and adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}