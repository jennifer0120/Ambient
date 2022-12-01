package com.example.ambientproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ambientproject.databinding.SessionRecyclerMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GroupSessionsFragment : Fragment() {
    private var _binding: SessionRecyclerMainBinding? = null
    private val binding get() = _binding!!
    private val focusSessionModel: FocusSessionViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SessionRecyclerMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.sessionRecyclerView
        val adapter = PopularSessionAdapter(focusSessionModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = null

        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        uiScope.launch {
            focusSessionModel.getTopPopularList(10).observe(viewLifecycleOwner) {
                    list ->
                adapter.submitList(list)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}