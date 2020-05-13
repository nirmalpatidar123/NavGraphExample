package com.example.mygallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mygallery.model.GalleryFile

@Database(entities = [GalleryFile::class], version = 1, exportSchema = false)
abstract class GalleryDatabase: RoomDatabase() {
    abstract fun galleryFileDao(): GalleryFileDao
}

private lateinit var INSTANCE: GalleryDatabase


fun getDatabase(context: Context): GalleryDatabase{
    synchronized(GalleryDatabase::class){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                GalleryDatabase::class.java, "app_db").build()
        }
    }
    return INSTANCE
}