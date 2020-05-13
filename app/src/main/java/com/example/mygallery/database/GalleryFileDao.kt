package com.example.mygallery.database

import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygallery.model.GalleryFile

@Dao
interface GalleryFileDao {
    @Query("SELECT * from GalleryFile")
    fun getAll(): LiveData<List<GalleryFile>>

    @Query("SELECT * from GalleryFile where mediaType = " + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)
    fun getAllImages(): LiveData<List<GalleryFile>>

    @Query("SELECT * from GalleryFile where mediaType = " + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
    fun getAllVideos(): LiveData<List<GalleryFile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(galleryFile: GalleryFile)
}