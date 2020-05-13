package com.example.mygallery.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GalleryFile(
    @PrimaryKey(autoGenerate = false) val id: Long, val name:String, val mediaType: Int, val mimeType: String, val dateAdded: Long)