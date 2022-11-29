package edu.cs371m.reddit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ambientproject.LabSound
import com.example.ambientproject.LabSoundViewModel
import com.example.ambientproject.databinding.SelectedLabSoundRowBinding

/**
 * Created by witchel on 8/25/2019
 */

// https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter
// Slick adapter that provides submitList, so you don't worry about how to update
// the list, you just submit a new one when you want to change the list and the
// Diff class computes the smallest set of changes that need to happen.
// NB: Both the old and new lists must both be in memory at the same time.
// So you can copy the old list, change it into a new list, then submit the new list.
//
// You can call adapterPosition to get the index of the selected item
class CreateFocusSessionRowAdapter(private val viewModel: LabSoundViewModel)
    : ListAdapter<LabSound, CreateFocusSessionRowAdapter.VH>(DataDiff()) {

    inner class VH(val binding: SelectedLabSoundRowBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = SelectedLabSoundRowBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val binding = holder.binding
        val item = getItem(position)
        binding.selectedMusicText.text = item.name
    }

    class DataDiff : DiffUtil.ItemCallback<LabSound>() {
        override fun areItemsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: LabSound, newItem: LabSound): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.image == newItem.image && oldItem.rawSongId == newItem.rawSongId
        }
    }
}

