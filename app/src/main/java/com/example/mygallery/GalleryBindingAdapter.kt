package com.example.mygallery

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mygallery.model.GalleryFile
import kotlinx.coroutines.runBlocking

@BindingAdapter("app:setVideoIconVisibility")
fun setVideoIconVisibility(view: View, mediaType: Int){
    view.visibility = if(mediaType==(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)) View.VISIBLE else View.GONE
}



@BindingAdapter("app:setUriToImageView")
fun setImageView(imageView: ImageView, galleryFile: GalleryFile){
    /*runBlocking {
       val bitmap = withContext(Dispatchers.Default) {
           getBitmap(
               imageView.context,
               galleryFile
           )
       }

        if (bitmap!=null){
            imageView.setImageBitmap(bitmap)
        }

    }*/

    val context = imageView.context

    runBlocking {
        val uri = getUri(context, galleryFile)
        Glide
            .with(context)
            .load(uri)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}

suspend fun getBitmap(context: Context, galleryFile: GalleryFile):Bitmap?{

    if (galleryFile.mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentUri: Uri = ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                galleryFile.id
            )

            return context.contentResolver.loadThumbnail(contentUri, Size(96, 96), null)
        }else{
            return MediaStore.Images.Thumbnails.getThumbnail(
                context.contentResolver, galleryFile.id,
                MediaStore.Images.Thumbnails.MICRO_KIND,
                null as BitmapFactory.Options?
            )
        }
    }else{

        val contentUri: Uri = ContentUris.withAppendedId(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            galleryFile.id
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context.contentResolver.loadThumbnail(contentUri, Size(96, 96), null)
        }else{
            return MediaStore.Video.Thumbnails.getThumbnail(
                context.contentResolver, galleryFile.id,
                MediaStore.Video.Thumbnails.MICRO_KIND,
                null as BitmapFactory.Options?
            )
        }
    }
}

suspend fun getUri(context: Context, galleryFile: GalleryFile):Uri?{

    if (galleryFile.mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE){
        return ContentUris.withAppendedId(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            galleryFile.id
        )
    }else{
        return ContentUris.withAppendedId(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            galleryFile.id
        )
    }
}





