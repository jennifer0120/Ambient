package com.example.ambientproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.RoomItemBinding
import com.example.ambientproject.databinding.SessionItemBinding

class SessionAdapter(private val focusSessionViewModel: FocusSessionViewModel)
    : ListAdapter<FocusSession, SessionAdapter.VH>(SessionDiff()) {
        companion object {
            val TAG = "SessionAdapter"
        }

    private fun getPos(holder: RecyclerView.ViewHolder): Int {
        val pos = holder.bindingAdapterPosition
        if (pos == RecyclerView.NO_POSITION) {
            return holder.absoluteAdapterPosition
        }
        return pos
    }

    override fun getItemCount(): Int {
        return focusSessionViewModel.getItemCount()
    }

    // ViewHolder patterns
    inner class VH(val sessionItemBinding: SessionItemBinding)
        : RecyclerView.ViewHolder(sessionItemBinding.root) {
        init {
            sessionItemBinding.root.setOnClickListener {
                val position = getPos(this)
                val context = it.context
                val item = focusSessionViewModel.getItemAt(position)
                item.let {
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionAdapter.VH {
        val sessionItemBinding = SessionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(sessionItemBinding)
    }

    override fun onBindViewHolder(holder: SessionAdapter.VH, position: Int) {
        val item = focusSessionViewModel.getItemAt(position)
        val binding = holder.sessionItemBinding
        if (item != null) {
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
        }
    }

    class SessionDiff : DiffUtil.ItemCallback<FocusSession>() {
        override fun areItemsTheSame(oldItem: FocusSession, newItem: FocusSession): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FocusSession, newItem: FocusSession): Boolean {
            return oldItem.id == newItem.id && oldItem.description == newItem.description && oldItem.title == newItem.title && oldItem.rawSongIdList == newItem.rawSongIdList
        }
    }
}