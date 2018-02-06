package com.thekeiron.hdscreentester

import android.content.res.Resources

class DeviceUtils internal constructor() {

    /**
     * Used to check if the device screen supports HD (1920x1080 pixels)
     *
     * @return true if the device is HD
     */
    val isScreenHD: Boolean
        get() {
            val displaySize = displaySize
            return displaySize.width >= SCREEN_SIZE_HD.width && displaySize.height >= SCREEN_SIZE_HD.height
        }

    /**
     * Get the size (width and height) of the display on the device
     *
     * @return Size The display size in pixels
     */
    val displaySize: Size
        get() {
            val metrics = Resources.getSystem().displayMetrics
            val height: Int
            val width: Int
            if (metrics.widthPixels > metrics.heightPixels) {
                height = metrics.heightPixels
                width = metrics.widthPixels
            } else {
                width = metrics.heightPixels
                height = metrics.widthPixels
            }
            return Size(width, height)
        }

    class Size(val width: Int, val height: Int)

    companion object {
        private val SCREEN_SIZE_UHD = Size(3840, 2160)
        private val SCREEN_SIZE_HD = Size(1920, 1080)
    }
}
