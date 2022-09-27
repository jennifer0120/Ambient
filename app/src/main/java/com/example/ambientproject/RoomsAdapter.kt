package com.example.ambientproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.RoomItemBinding
import com.example.ambientproject.databinding.RowBinding

class RoomsAdapter(private val roomViewModel: RoomViewModel)
    : RecyclerView.Adapter<RoomsAdapter.VH>() {
        companion object {
            val TAG = "RoomsAdapter"
        }

    private fun getPos(holder: RecyclerView.ViewHolder): Int {
        val pos = holder.bindingAdapterPosition
        if (pos == RecyclerView.NO_POSITION) {
            return holder.absoluteAdapterPosition
        }
        return pos
    }

    override fun getItemCount(): Int {
        return roomViewModel.getItemCount()
    }

    // ViewHolder pattern
    inner class VH(val roomItemBinding: RoomItemBinding)
        : RecyclerView.ViewHolder(roomItemBinding.root) {
        init {
            roomItemBinding.root.setOnClickListener {
                val position = getPos(this)
                val context = it.context
                val item = roomViewModel.getItemAt(position)
                item.let {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsAdapter.VH {
        val roomItemBinding = RoomItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(roomItemBinding)
    }

    override fun onBindViewHolder(holder: RoomsAdapter.VH, position: Int) {
        val item = roomViewModel.getItemAt(position)
        val binding = holder.roomItemBinding
        binding.itemText.text = item.name
    }

}