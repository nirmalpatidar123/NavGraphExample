package com.example.mygallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygallery.model.GalleryFile
import com.example.mygallery.repository.GalleryRepository

class DetailViewModel(private val repository: GalleryRepository, private val galleryFile: GalleryFile): ViewModel() {

}

class DetailViewModelFactory(val repository: GalleryRepository, val galleryFile: GalleryFile): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}