package com.example.mygallery.viewmodel

import androidx.lifecycle.*
import com.example.mygallery.model.GalleryFile
import com.example.mygallery.repository.GalleryRepository
import kotlinx.coroutines.launch

class GalleryViewModel(private val repository: GalleryRepository): ViewModel() {

    val galleryFileList : LiveData<List<GalleryFile>> = repository.getGalleryFiles()

    fun fetchMediaFromStorage(){
        viewModelScope.launch {
            repository.fetchMediaFromStorage()
        }
    }
}

class GalleryViewModelFactory(val repository: GalleryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}