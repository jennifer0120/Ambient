package com.example.ambientproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.SessionItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


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

                    val intent = Intent(context, StartSessionActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("sessionId", item?.id.toString())
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionAdapter.VH {
        Log.i("XXX", "onCreateViewHolder!!")
        val sessionItemBinding = SessionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(sessionItemBinding)
    }

    override fun onBindViewHolder(holder: SessionAdapter.VH, position: Int) {
        Log.i("XXX", "onBindViewHolder!!!")
        val item = focusSessionViewModel.getItemAt(position)
        val binding = holder.sessionItemBinding
        if (item != null) {
            binding.itemTitle.text = item.title
            if (binding.itemDescription.text.isNotEmpty()) {
                binding.itemDescription.text = item.description
            }
        }
    }

    class SessionDiff : DiffUtil.ItemCallback<FocusSession>() {
        override fun areItemsTheSame(oldItem: FocusSession, newItem: FocusSession): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FocusSession, newItem: FocusSession): Boolean {
            return oldItem.id == newItem.id && oldItem.description == newItem.description && oldItem.title == newItem.title && oldItem.labSoundIds == newItem.labSoundIds
        }
    }
}