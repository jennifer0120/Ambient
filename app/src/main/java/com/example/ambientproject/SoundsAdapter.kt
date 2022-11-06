package com.example.ambientproject

import android.graphics.Color
import android.graphics.ColorFilter
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.databinding.RowBinding

class SoundsAdapter(private val viewModel: MainViewModel)
    : RecyclerView.Adapter<SoundsAdapter.VH>() {
        companion object {
            val TAG = "SoundsAdapter"
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
        binding.itemText.text = item.name
        binding.itemPic.setImageResource(item.icon)
        binding.itemPic.setColorFilter(Color.parseColor("#B1BCBE"))
    }

    fun setItemDisplay(turnedOn: Boolean, rowBinding: RowBinding) {
        if (turnedOn) {
            rowBinding.cardViewConstraint.setBackgroundColor(Color.parseColor("#87A2FB"))
            rowBinding.itemPic.setColorFilter(Color.parseColor("#EEEEEE"))
            rowBinding.itemText.setTextColor(Color.parseColor("#EEEEEE"))
        } else {
            rowBinding.cardViewConstraint.setBackgroundColor(Color.parseColor("#F9F6F2"))
            rowBinding.itemPic.setColorFilter(Color.parseColor("#B1BCBE"))
            rowBinding.itemText.setTextColor(Color.parseColor("#000000"))
        }
    }
    fun playSongClip() {

    }
}
