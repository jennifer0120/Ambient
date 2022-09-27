package com.example.ambientproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ambientproject.databinding.RecyclerMainBinding

class RoomsFragment : Fragment() {
    companion object {
        val TAG: String = RoomsFragment::class.java.simpleName
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
        binding.recyclerView.setBackgroundResource(R.drawable.back_drawable)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Mixed"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}