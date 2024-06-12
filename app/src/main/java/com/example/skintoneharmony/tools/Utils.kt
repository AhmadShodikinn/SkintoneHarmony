package com.example.skintoneharmony.tools

import android.content.Context
import java.io.File

object Utils {
    private const val timeStamp = "yyyy-MM-dd-HH-ss-SSS"

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }
    fun String.toParagraphCase(): String {
        return this.split(" ").joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
    }

}