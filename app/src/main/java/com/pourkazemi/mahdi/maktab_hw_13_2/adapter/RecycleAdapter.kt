package com.pourkazemi.mahdi.maktab_hw_13_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pourkazemi.mahdi.maktab_hw_13_2.R
import com.pourkazemi.mahdi.maktab_hw_13_2.databinding.ItemModelBinding
import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo
import javax.inject.Inject

class RecycleAdapter @Inject constructor() : ListAdapter<Photo, RecycleAdapter.PhotoViewHolder>(PhotoDiffCall) {
    class PhotoViewHolder(
        private val binding: ItemModelBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun mBind(photo: Photo) {
            binding.photo = photo
            binding.executePendingBindings()
        }

        companion object {
            fun create(
                inflater: LayoutInflater,
                layoutId: Int,
                parent: ViewGroup?,
                attachToRoot: Boolean
            ) = PhotoViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    layoutId,
                    parent,
                    attachToRoot
                )
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
       return PhotoViewHolder.create(
            LayoutInflater.from(parent.context),
            R.layout.item_model,
           parent,
           false
        )
    }

    override fun onBindViewHolder(
        holder: PhotoViewHolder,
        position: Int
    ) {
        holder.mBind(getItem(position))
    }

}

object PhotoDiffCall : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

}
