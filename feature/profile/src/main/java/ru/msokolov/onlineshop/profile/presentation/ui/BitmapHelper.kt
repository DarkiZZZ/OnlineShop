package ru.msokolov.onlineshop.profile.presentation.ui

import android.graphics.*
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class BitmapHelper @Inject constructor(){

    fun getCircularBitmap(bitmap: Bitmap): Bitmap {
        val output = if (bitmap.width > bitmap.height) {
            Bitmap.createBitmap(bitmap.height, bitmap.height, Bitmap.Config.ARGB_8888)
        } else {
            Bitmap.createBitmap(bitmap.width, bitmap.width, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val r = if (bitmap.width > bitmap.height) {
            (bitmap.height / 2).toFloat()
        } else {
            (bitmap.width / 2).toFloat()
        }
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(r, r, r, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    fun covertBitmapToBlob(bitmap: Bitmap): ByteArray {
        val bos = ByteArrayOutputStream()
        val newBitmap = Bitmap.createScaledBitmap(bitmap,
            IMAGE_SIZE_DST,
            IMAGE_SIZE_DST, false)
        newBitmap.compress(Bitmap.CompressFormat.PNG, IMAGE_SIZE_QUALITY, bos)
        return bos.toByteArray()
    }

    fun convertBlobToBitmap(photoBlob: ByteArray): Bitmap {
        val bitmap = BitmapFactory.decodeByteArray(photoBlob, 0, photoBlob.size)
        return BitmapHelper().getCircularBitmap(bitmap)
    }

    companion object{
        private const val IMAGE_SIZE_DST = 200
        private const val IMAGE_SIZE_QUALITY = 10
    }
}