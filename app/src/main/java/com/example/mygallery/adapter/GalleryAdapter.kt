package com.example.mygallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygallery.interfaces.GalleryItemClickListener
import com.example.mygallery.databinding.GalleryListItemBinding
import com.example.mygallery.model.GalleryFile

class GalleryAdapter internal constructor(val context: Context):
    RecyclerView.Adapter<GalleryAdapter.GalleryAdapterViewHolder>(),
    GalleryItemClickListener {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var galleryFileList =  emptyList<GalleryFile>()

    inner class GalleryAdapterViewHolder(binding: GalleryListItemBinding): RecyclerView.ViewHolder(binding.root){
        val binding: GalleryListItemBinding by lazy {
            binding
        }
    }

    internal fun setGalleryFiles(galleryFileList: List<GalleryFile>){
        this.galleryFileList = galleryFileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapterViewHolder {
        val binding: GalleryListItemBinding = GalleryListItemBinding.inflate(inflater, parent, false)
        binding.root.layoutParams.height = (parent.width-40)/3
        binding.root.layoutParams.width = (parent.width-40)/3
        return GalleryAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return galleryFileList.size
    }

    override fun onBindViewHolder(holder: GalleryAdapterViewHolder, position: Int) {
        holder.binding.galleryFile = galleryFileList[position]
        holder.binding.galleryItemClickListener = this
        holder.binding.executePendingBindings()
    }

    override fun onGalleryItemClicked(galleryFile: GalleryFile) {

    }
}