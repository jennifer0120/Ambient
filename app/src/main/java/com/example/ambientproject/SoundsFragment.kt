package com.example.ambientproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ambientproject.databinding.RecyclerMainBinding

class SoundsFragment: Fragment() {
    companion object {
        val TAG: String = SoundsFragment::class.java.simpleName
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
        binding.recyclerView.setBackgroundResource(R.drawable.back_drawable)
        binding.recyclerView.layoutManager = GridLayoutManager(binding.recyclerView.context, 3)
        binding.recyclerView.adapter = SoundsAdapter(viewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}