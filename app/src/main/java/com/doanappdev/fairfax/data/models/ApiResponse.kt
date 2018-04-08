package com.doanappdev.fairfax.data.models

import java.util.*

data class NewsResponse(
        val id: Int,
        val url: String,
        val lastModified: Long,
        val displayName: String,
        val onTime: Long,
        val sponsored: Boolean,
        val assets: List<Asset>,
        val assetType: String,
        val timeStamp: Long
)

data class Asset(
        val id: Int,
        val url: String,
        val lastModified: Long,
        val onTime: Long,
        val sponsored: Boolean,
        val headline: String,
        val indexHeadline: String,
        val tabletHeadline: String,
        val theAbstract: String,
        val byLine: String,
        val numberOfComments: Int,
        val relatedImages: List<Image>,
        val timeStamp: Long
) {
    var date: Date? = null
    var smallestImage: Image? = null

    /**
     * this method has a test method in ExampleUnitTest
     * use the test method to verify logic and replace code
     * here by copying code from test to here, if logic in
     * test is changed
     */
    fun findSmallestImage() {
        smallestImage = relatedImages[0]
        var minWidth = relatedImages[0].width
        var minHeight = relatedImages[0].height

        relatedImages.forEach {
            if (it.height != 0) {
                when((it.width <= minWidth && it.height <= minHeight)) {
                    true -> {
                        minWidth = it.width
                        minHeight = it.height
                        smallestImage = it
                    }
                }
            }
        }
    }
}

data class Image(
        val id: Int,
        val url: String,
        val description: String,
        val photographer: String,
        val width: Int,
        val height: Int,
        val timeStamp: Long
)