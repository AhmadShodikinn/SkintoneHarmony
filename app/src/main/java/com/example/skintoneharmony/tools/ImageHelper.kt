package com.example.skintoneharmony.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object ImageHelper {
//    fun loadImageFromUri(context: Context, uri: Uri): Bitmap? {
//        var inputStream: InputStream? = null
//        try {
//            inputStream = context.contentResolver.openInputStream(uri)
//            return BitmapFactory.decodeStream(inputStream)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            inputStream?.close()
//        }
//        return null
//    }

    fun loadImageFromUri(context: Context, imageUri: Uri, targetWidth: Int, targetHeight: Int) : Bitmap? {
        return try {
            // Menggunakan BitmapFactory untuk memuat gambar dari URI
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri), null, options)

            // Menghitung faktor skala untuk menyesuaikan ukuran gambar ke targetWidth dan targetHeight
            val scaleFactor = Math.min(options.outWidth / targetWidth, options.outHeight / targetHeight)

            // Mengatur opsi untuk memuat gambar dengan faktor skala yang benar
            options.inJustDecodeBounds = false
            options.inSampleSize = scaleFactor

            // Memuat gambar aktual dengan opsi yang disetel
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri), null, options)

            // Anda dapat melakukan preprocessing tambahan di sini jika diperlukan, seperti normalisasi

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(bitmap.width * bitmap.height * 3 * 4) // 3 channels (RGB) * 4 bytes per float
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in pixels) {
            val r = ((pixelValue shr 16) and 0xFF).toFloat() / 255.0f
            val g = ((pixelValue shr 8) and 0xFF).toFloat() / 255.0f
            val b = (pixelValue and 0xFF).toFloat() / 255.0f

            byteBuffer.putFloat(r)
            byteBuffer.putFloat(g)
            byteBuffer.putFloat(b)
        }

        byteBuffer.flip()
        return byteBuffer
    }

}