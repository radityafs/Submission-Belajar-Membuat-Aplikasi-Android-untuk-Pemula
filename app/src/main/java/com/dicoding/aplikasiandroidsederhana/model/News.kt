package com.dicoding.aplikasiandroidsederhana.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class News(
    var title: String = "",
    var description: String = "",
    var image: String = "",
    var author : String = "",
    var author_image : String = "",
    var date : String = "",
    var category : String = "",
) : Parcelable