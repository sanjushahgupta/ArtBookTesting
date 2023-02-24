package com.digitalaya.model

import org.w3c.dom.Comment

data class ImageResult(
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val largeImageURL: String,
    val previewURL: String

)


