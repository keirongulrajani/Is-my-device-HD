package com.thekeiron.hdscreentester

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDeviceUtils = DeviceUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val displaySize = mDeviceUtils.displaySize

        screenDimensionsTextView.text = getString(R.string.screen_dimens, displaySize.width, displaySize.height)

        val screenHD = mDeviceUtils.isScreenHD

        screenTypeTextView.text = getString(if (screenHD) R.string.hd_message else R.string.not_hd_message)
        screenTypeTextView.setTextColor(resources.getColor(if (screenHD) R.color.green else R.color.red))

        val anim = AnimationUtils.loadAnimation(applicationContext, if (screenHD) R.anim.zoom else R.anim.shake)
        screenTypeTextView.startAnimation(anim)
    }
}
