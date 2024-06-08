package com.example.skintoneharmony.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object ImageHelper {
    fun loadImageFromUri(context: Context, uri: Uri, imageWidth: Int, imageHeight: Int): Bitmap? {
        var inputStream: InputStream? = null
        try {
            inputStream = context.contentResolver.openInputStream(uri)
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()

            options.inSampleSize = calculateInSampleSize(options, imageWidth, imageHeight)
            inputStream = context.contentResolver.openInputStream(uri)
            options.inJustDecodeBounds = false
            val bitmap = BitmapFactory.decodeStream(inputStream, null, options)

            return bitmap?.let { Bitmap.createScaledBitmap(it, imageWidth, imageHeight, true) }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return null
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

    fun convertBitmapToByteBuffer(bitmap: Bitmap, imageWidth: Int, imageHeight: Int): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageWidth * imageHeight * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(imageWidth * imageHeight)
        bitmap.getPixels(intValues, 0, imageWidth, 0, 0, imageWidth, imageHeight) // <-- Perubahan disini
        var pixel = 0
        for (i in 0 until imageHeight) {
            for (j in 0 until imageWidth) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16 and 0xFF) - 127.5f) / 127.5f)
                byteBuffer.putFloat(((value shr 8 and 0xFF) - 127.5f) / 127.5f)
                byteBuffer.putFloat(((value and 0xFF) - 127.5f) / 127.5f)
            }
        }
        return byteBuffer
    }

}