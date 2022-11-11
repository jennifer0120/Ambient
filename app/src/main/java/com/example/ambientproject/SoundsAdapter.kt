package com.example.ambientproject

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.RowBinding

class SoundsAdapter(private val viewModel: MainViewModel)
    : RecyclerView.Adapter<SoundsAdapter.VH>() {
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
    inner class VH(val rowBinding: RowBinding)
        : RecyclerView.ViewHolder(rowBinding.root) {
            init {
                rowBinding.root.setOnClickListener {
                    val position = getPos(this)
                    val context = it.context
                    val item = viewModel.getItemAt(position)
                    viewModel.toggleTunedOn(item)
                    val turnedOn = viewModel.isTurnedOn(item)
                    item.let {
                        setItemDisplay(turnedOn, rowBinding)
                        playSongClip(turnedOn, item, context)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val rowBinding = RowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return VH(rowBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = viewModel.getItemAt(position)
        val binding = holder.rowBinding
        binding.itemPic.setBackgroundResource(item.image)
        binding.itemText.text = item.name
    }

    fun setItemDisplay(turnedOn: Boolean, rowBinding: RowBinding) {
        if (turnedOn) {
            rowBinding.cardView.alpha = 1.0f
            rowBinding.cardView.setCardBackgroundColor(Color.parseColor("#023047"))
        } else {
            rowBinding.cardView.alpha = 0.5f
            rowBinding.cardView.setCardBackgroundColor(Color.parseColor("#219ebc"))
        }
    }

    fun playSongClip(turnedOn: Boolean, item: Data, context: Context) {
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
}
