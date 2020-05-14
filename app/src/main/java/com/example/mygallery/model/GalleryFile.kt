package com.example.mygallery.model

import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class GalleryFile(
    @PrimaryKey(autoGenerate = false) val id: Long, val name:String, val mediaType: Int, val mimeType: String, val dateAdded: Long,
val duration: Long, val size: Long,val width: Long, val height: Long, val orientation: Int):Parcelable