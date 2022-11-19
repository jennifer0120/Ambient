package com.example.ambientproject

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.LabSoundItemBinding

class LabSoundAdapter2(private val viewModel: LabSoundViewModel)
    : ListAdapter<LabSound, LabSoundAdapter2.VH>(LabSoundDiff()) {
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
                val turnedOn = viewModel.isTurnedOn(item)
                item.let {
                    setItemDisplay(turnedOn, labSoundItemBinding)
                    playSongClip(turnedOn, item, context)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabSoundAdapter2.VH {
        val rowBinding = LabSoundItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(rowBinding)
    }

    override fun onBindViewHolder(holder: LabSoundAdapter2.VH, position: Int) {
        val item = viewModel.getItemAt(position)
        val binding = holder.labSoundItemBinding
        binding.itemPic.setBackgroundResource(item.image)
        binding.itemText.text = item.name
    }

    fun setItemDisplay(turnedOn: Boolean, labSoundItemBinding: LabSoundItemBinding) {
        if (turnedOn) {
            labSoundItemBinding.cardView.alpha = 1.0f
            labSoundItemBinding.cardView.setCardBackgroundColor(Color.parseColor("#023047"))
        } else {
            labSoundItemBinding.cardView.alpha = 0.5f
            labSoundItemBinding.cardView.setCardBackgroundColor(Color.parseColor("#219ebc"))
        }
    }

    fun playSongClip(turnedOn: Boolean, item: LabSound, context: Context) {
        if (turnedOn) {
            playerMap[item.id] = MediaPlayer()
            playerMap[item.id]!!.setDataSource(item.rawSongId)
            playerMap[item.id]!!.prepare()
            playerMap[item.id]!!.start()
        } else {
            if (playerMap[item.id] != null) {
                playerMap[item.id]!!.stop()
                playerMap[item.id]!!.reset()
                playerMap[item.id]!!.release()
            }

        }
    }

    class LabSoundDiff : DiffUtil.ItemCallback<LabSound>() {
        override fun areItemsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.image == newItem.image && oldItem.group == newItem.group && oldItem.rawSongId == newItem.rawSongId
        }
    }
}
