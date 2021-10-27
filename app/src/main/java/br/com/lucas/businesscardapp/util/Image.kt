package br.com.lucas.businesscardapp.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import br.com.lucas.businesscardapp.R
import br.com.lucas.businesscardapp.core.extensions.toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class Image {
    companion object{
        fun share(context: Context, view: View){
            val bitmap = getScreenShotFromView(view)

            bitmap?.let{
                saveMediaToStorage(context, bitmap)
            }
        }

        private fun getScreenShotFromView(card: View): Bitmap?{
            var screenShot: Bitmap? = null
            try {
                screenShot = Bitmap.createBitmap(
                    card.measuredWidth,
                    card.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(screenShot)
                card.draw(canvas)
            } catch (e: Exception){
                Log.e("Error", "Fail on screenshot $e")
            }

            return screenShot
        }

        private fun saveMediaToStorage(context: Context, bitmap: Bitmap){
            val fileName = "${System.currentTimeMillis()}.jpg"

            var fos: OutputStream? = null

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                // Saving image on Android 10 devices
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    }
                }
            } else {
                // Saving image on Android below 10 or Q
                val imagesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDirectory, fileName)
                val imageUri = FileProvider.getUriForFile(context,
                    context.applicationContext.packageName + ".provider", image)
                shareIntent(context, imageUri)
                fos = FileOutputStream(image)
            }

            fos?.use{
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                context.toast("Screenshot was taken successfully")
            }
        }

        private fun shareIntent(context: Context, imageUri: Uri){
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                type = "image/jpeg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.resources.getText(R.string.label_share)
                )
            )
        }
    }
}