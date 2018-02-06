package com.thekeiron.hdscreentester

import android.content.res.Resources
import android.util.DisplayMetrics

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

import org.junit.Assert.*
import org.mockito.Mockito.`when`

@RunWith(PowerMockRunner::class)
@PrepareForTest(Resources::class)
class DeviceUtilsTest {
    private lateinit var mCut: DeviceUtils

    @Mock
    private lateinit var mResources: Resources
    private lateinit var mMetrics: DisplayMetrics

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Resources::class.java)
        PowerMockito.`when`(Resources.getSystem()).thenReturn(mResources)

        mMetrics = DisplayMetrics()
        mMetrics.widthPixels = HD_SIZE_WIDTH
        mMetrics.heightPixels = HD_SIZE_HEIGHT
        `when`(mResources.displayMetrics).thenReturn(mMetrics)

        mCut = DeviceUtils()
    }

    @Test
    fun givenDeviceWithScreenSizeEqualTo1080By1920WhenIsScreenHdReturnsTrue() {
        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertTrue(screenHD)
    }

    @Test
    fun givenDeviceWithScreenHeightMoreThan1080WhenIsScreenHdReturnsTrue() {
        // Given
        mMetrics.heightPixels = 1081

        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertTrue(screenHD)
    }

    @Test
    fun givenDeviceWithScreenDimensReversedWithHdResolutionWhenIsScreenHdReturnsTrue() {
        // Given
        mMetrics.heightPixels = HD_SIZE_WIDTH
        mMetrics.widthPixels = HD_SIZE_HEIGHT

        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertTrue(screenHD)
    }

    @Test
    fun givenDeviceWithScreenWidthMoreThan1920WhenIsScreenHdReturnsTrue() {
        // Given
        mMetrics.widthPixels = 1921

        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertTrue(screenHD)
    }

    @Test
    fun givenDeviceWithScreenHeightLessThan1080WhenIsScreenHdReturnsFalse() {
        // Given
        mMetrics.heightPixels = 1079

        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertFalse(screenHD)
    }

    @Test
    fun givenDeviceWithScreenWidthLessThan1920WhenIsScreenHdReturnsFalse() {
        // Given
        mMetrics.widthPixels = 1919

        // When
        val screenHD = mCut.isScreenHD

        // Then
        assertFalse(screenHD)
    }

    companion object {

        private val HD_SIZE_WIDTH = 1920
        private val HD_SIZE_HEIGHT = 1080
    }
}