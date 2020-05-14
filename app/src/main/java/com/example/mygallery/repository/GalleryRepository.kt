package com.example.mygallery.repository

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContentResolverCompat
import androidx.lifecycle.LiveData
import com.example.mygallery.database.GalleryDatabase
import com.example.mygallery.model.GalleryFile

class GalleryRepository(val context: Context, val db: GalleryDatabase) {

    fun getGalleryFiles(): LiveData<List<GalleryFile>> {
        return db.galleryFileDao().getAll()
    }

    suspend fun fetchMediaFromStorage(){
        val selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE + " OR " + MediaStore.Files.FileColumns.MEDIA_TYPE + "="+ MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
        val selectionArgs = null
        val sortOrder = "${MediaStore.Files.FileColumns.DATE_ADDED} ASC"

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.DURATION,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.WIDTH,
            MediaStore.Files.FileColumns.HEIGHT,
            MediaStore.Files.FileColumns.ORIENTATION
        )

        val query = ContentResolverCompat.query(context.contentResolver, MediaStore.Files.getContentUri("external"),
        projection, selection, selectionArgs, sortOrder, null)

        query?.use {cursor ->
            // cache column indices
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
            val mediaTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)

            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.HEIGHT)
            val orientationColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.ORIENTATION)

            while (cursor.moveToNext()){
                val fileId = cursor.getLong(idColumn)
                val dateAdded = cursor.getLong(dateAddedColumn)
                val mediaType = cursor.getInt(mediaTypeColumn)
                val mimeType = cursor.getString(mimeTypeColumn)
                val title = cursor.getString(titleColumn)
                val duration = cursor.getLong(durationColumn)
                val size = cursor.getLong(sizeColumn)
                val width = cursor.getLong(widthColumn)
                val height = cursor.getLong(heightColumn)
                val orientation = cursor.getInt(orientationColumn)


                val galleryFile = GalleryFile(
                    fileId,
                    title,
                    mediaType,
                    mimeType,
                    dateAdded,
                    duration, size, width, height, orientation
                )
                Log.e("gallery data: ", galleryFile.toString())
                db.galleryFileDao().insert(galleryFile)
            }

        }
    }

}