package com.example.ambientproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.SelectedRowBinding

class OldCreateFocusSessionSelectedAdapter(private val viewModel: MainViewModel)
    : RecyclerView.Adapter<OldCreateFocusSessionSelectedAdapter.VH>() {
        companion object {
            val TAG = "CreateFocusSessionSelectedAdapter"
        }

    private fun getPos(holder: RecyclerView.ViewHolder): Int {
        Log.i("XXX", "getPos")
        val pos = holder.bindingAdapterPosition
        if (pos == RecyclerView.NO_POSITION) {
            return holder.absoluteAdapterPosition
        }
        return pos
    }

    override fun getItemCount(): Int {
        Log.i("XXX", "getItemCount")
        val test = viewModel.getTurnedOnAmbientItemCount()
        Log.i("XXX", "return ${test}")
        return test
    }

    // ViewHolder pattern
    inner class VH(val selectedRowBinding: SelectedRowBinding)
        : RecyclerView.ViewHolder(selectedRowBinding.root) {
        init {
            Log.i("XXX", "VH")
            selectedRowBinding.root.setOnClickListener {
                val position = getPos(this)
                val item = viewModel.getSelectedItemAt(position)
                Log.i("XXX", "item: $item")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldCreateFocusSessionSelectedAdapter.VH {
        Log.i("XXX", "onCreateViewHolder")
        val selectedRowBinding = SelectedRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return VH(selectedRowBinding)
    }

    override fun onBindViewHolder(holder: OldCreateFocusSessionSelectedAdapter.VH, position: Int) {
        Log.i("XXX", "onBindViewHolder")
        val item = viewModel.getItemAt(position)
        val binding = holder.selectedRowBinding
        binding.selectedMusicText.text = item.name
    }
    }

    