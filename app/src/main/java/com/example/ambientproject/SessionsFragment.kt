package com.example.ambientproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ambientproject.databinding.SessionRecyclerMainBinding

class SessionsFragment : Fragment() {
    companion object {
        val TAG: String = SessionsFragment::class.java.simpleName
        fun newInstance(): SessionsFragment {
            return SessionsFragment()
        }
    }

    // https://developer.android.com/topic/libraries/view-binding#fragments
    private var _binding: SessionRecyclerMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
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
        val adapter = SessionAdapter(focusSessionModel)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = null

        focusSessionModel.getList().observe(viewLifecycleOwner) {
            list ->
            Log.i("XXX", "list: $list")
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}