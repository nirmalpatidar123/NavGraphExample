package com.example.mygallery.interfaces

import com.example.mygallery.model.GalleryFile

interface GalleryItemClickListener {
    fun onGalleryItemClicked(galleryFile: GalleryFile)
}