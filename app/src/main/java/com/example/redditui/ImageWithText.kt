package com.example.redditui

import androidx.compose.ui.graphics.painter.Painter

data class ImageWithText(
    val image: Painter,
    val subreddit: String,
    val userName: String,
    val headline:String,
    val supportingText:String
)
