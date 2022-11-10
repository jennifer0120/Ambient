package com.example.ambientproject

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.util.Log
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
        binding.rainRecyclerView.layoutManager = GridLayoutManager(binding.rainRecyclerView.context, 2)
        binding.rainRecyclerView.adapter = SoundsAdapter(viewModel)

        val turnedOnAmbientItemList = viewModel.getTurnedOnAmbientItemList().observe(viewLifecycleOwner) {
            itemList ->
            if (itemList.isEmpty()) {
                binding.createSession.setBackgroundColor(Color.parseColor("#EFEFEF"))
                binding.createSession.setTextColor(Color.parseColor("#000000"))
            } else {
                binding.createSession.setBackgroundResource(R.color.purple_500)
                binding.createSession.setTextColor(Color.parseColor("#FFFFFF"))
            }

            binding.createSession.setOnClickListener {
                if (itemList.isNotEmpty()) {
                    val intent = Intent(context, CreateFocusSessionActivity::class.java)
                    context!!.startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}