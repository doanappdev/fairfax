package com.doanappdev.fairfax.data.models

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
        val acceptComments: String,
        val numberOfComments: Int
)
