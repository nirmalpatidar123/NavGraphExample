package com.example.mygallery

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.text.format.Formatter
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mygallery.model.GalleryFile
import com.example.mygallery.utils.Utility
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.util.*


@BindingAdapter("app:setVideoIconVisibility")
fun setVideoIconVisibility(view: View, mediaType: Int) {
    view.visibility =
        if (mediaType == (MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)) View.VISIBLE else View.GONE
}

@BindingAdapter("app:setUriToImageView", "app:textView")
fun setImageView(imageView: ImageView, galleryFile: GalleryFile, textView: TextView) {

    val context = imageView.context
    runBlocking {
        val uri = getUri(galleryFile)
        Glide
            .with(context).asBitmap()
            .load(uri)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .listener(object: RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    textView.setTextColor(ContextCompat.getColor(context,android.R.color.holo_purple))
                    Log.e("my color","failed " + galleryFile.name)
                    textView.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {

                        var left = 0
                        var top = 0
                        var right = resource.width
                        var bottom = resource.height

                        if (resource.height>resource.width){
                            bottom = resource.height - (resource.height-resource.width)/2
                            if (bottom>50){
                                top = bottom-50
                            }
                        }else{
                            top = resource.height-50
                        }

                        val p: Palette = Palette.from(resource).setRegion(left, top, right, bottom).generate()
                        when {
                            p.dominantSwatch!=null -> {
                                Log.e("my color","dominantSwatch " + galleryFile.name)
                                textView.setTextColor(p.dominantSwatch!!.titleTextColor)
                            }
                            p.darkVibrantSwatch!=null -> {
                                Log.e("my color","darkVibrantSwatch " + galleryFile.name)
                                textView.setTextColor(p.darkVibrantSwatch!!.titleTextColor)
                            }
                            p.vibrantSwatch!=null -> {
                                Log.e("my color","vibrantSwatch " + galleryFile.name)
                                textView.setTextColor(p.vibrantSwatch!!.titleTextColor)
                            }
                            p.mutedSwatch!=null -> {
                                Log.e("my color","mutedSwatch " + galleryFile.name)
                                textView.setTextColor(p.mutedSwatch!!.titleTextColor)
                            }
                            p.lightMutedSwatch!=null -> {
                                Log.e("my color","lightMutedSwatch " + galleryFile.name)
                                textView.setTextColor(p.lightMutedSwatch!!.titleTextColor)
                            }
                            p.lightVibrantSwatch!=null -> {
                                Log.e("my color","lightVibrantSwatch " + galleryFile.name)
                                textView.setTextColor(p.lightVibrantSwatch!!.titleTextColor)
                            }
                            p.darkMutedSwatch!=null -> {
                                Log.e("my color","darkMutedSwatch " + galleryFile.name)
                                textView.setTextColor(p.darkMutedSwatch!!.titleTextColor)
                            }
                            else -> {
                                Log.e("my color","not found " + galleryFile.name)
                                textView.setTextColor(ContextCompat.getColor(context,android.R.color.white))
                            }
                        }
                        textView.visibility = View.VISIBLE

                    }
                    return false
                }
            })
            .into(imageView)
    }
}

@BindingAdapter("app:setUriToImageView")
fun setImageView(imageView: ImageView, galleryFile: GalleryFile){
    val context = imageView.context
    runBlocking {
        val uri = getUri(galleryFile)
        Glide
            .with(context)
            .load(uri)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}

fun getUri(galleryFile: GalleryFile): Uri? {

    if (galleryFile.mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
        return ContentUris.withAppendedId(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            galleryFile.id
        )
    } else {
        return ContentUris.withAppendedId(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            galleryFile.id
        )
    }
}

@BindingAdapter("aap:setMediaType")
fun setMediaType(textView: TextView, galleryFile: GalleryFile){
    val context = textView.context
    textView.text = "Media Type: " + galleryFile.mimeType
}

@BindingAdapter("app:setTimeStampToDate")
fun setTimeStampToDate(textView: TextView, dateAdded: Long){
    textView.text = "Date Created: "+ Utility.timestampToDate(dateAdded, Utility.DATE_FORMAT_STANDARD)
}

@BindingAdapter("app:setDurationText")
fun setDurationText(textView: TextView, galleryFile: GalleryFile){
    if (galleryFile.mediaType == MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE) {
        textView.visibility = View.GONE
    }else{
        textView.text = "Duration: " + Utility.milliSecondToTime(galleryFile.duration)
    }
}

@BindingAdapter("app:setSizeText")
fun setSizeText(textView: TextView, byteSize: Long){
    textView.text = "Media Size: "+ Formatter.formatFileSize(textView.context, byteSize)
}






