package com.example.ambientproject

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.LabSoundItemBinding

class LabSoundAdapter(private val viewModel: LabSoundViewModel)
    : ListAdapter<LabSound, LabSoundAdapter.VH>(LabSoundDiff()) {
    companion object {
        val TAG = "SoundsAdapter"
        val playerMap: HashMap<String, MediaPlayer> = HashMap()
    }

    private fun getPos(holder: RecyclerView.ViewHolder): Int {
        val pos = holder.bindingAdapterPosition
        if (pos == RecyclerView.NO_POSITION) {
            return holder.absoluteAdapterPosition
        }
        return pos
    }

    override fun getItemCount(): Int {
        return viewModel.getItemCount()
    }

    // ViewHolder pattern
    inner class VH(val labSoundItemBinding: LabSoundItemBinding)
        : RecyclerView.ViewHolder(labSoundItemBinding.root) {
        init {
            labSoundItemBinding.root.setOnClickListener {
                val position = getPos(this)
                val context = it.context
                val item = viewModel.getItemAt(position)
                viewModel.toggleTunedOn(item)
                val turnedOn = viewModel.isTurnedOn2(item.id)
                item.let {
                    setItemDisplay(turnedOn, labSoundItemBinding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabSoundAdapter.VH {
        val rowBinding = LabSoundItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(rowBinding)
    }

    override fun onBindViewHolder(holder: LabSoundAdapter.VH, position: Int) {
        val item = viewModel.getItemAt(position)
        val binding = holder.labSoundItemBinding
        binding.itemPic.setBackgroundResource(item.image)
        binding.itemText.text = item.name
    }

    fun setItemDisplay(turnedOn: Boolean, labSoundItemBinding: LabSoundItemBinding) {
        if (turnedOn) {
            labSoundItemBinding.cardView.setCardBackgroundColor(Color.parseColor("#fcf6bd"))
            labSoundItemBinding.itemText.setTextColor(Color.parseColor("#fca311"))
        } else {
            labSoundItemBinding.cardView.setCardBackgroundColor(Color.parseColor("#a9def9"))
            labSoundItemBinding.itemText.setTextColor(Color.parseColor("#AA1363DF"))
        }
    }

    class LabSoundDiff : DiffUtil.ItemCallback<LabSound>() {
        override fun areItemsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.image == newItem.image && oldItem.rawSongId == newItem.rawSongId
        }
    }
}
