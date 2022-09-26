package com.example.ambientproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
                    item.let {
                        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
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
        binding.rowText.text = item.name
        binding.rowPic.setImageResource(item.icon)
    }
}
