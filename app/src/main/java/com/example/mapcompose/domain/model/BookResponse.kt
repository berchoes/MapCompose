package com.example.mapcompose.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Berk Ç. on 20.04.2022.
 */
data class BookResponse(
    val busName: String,
    val id: Int?,
    val time: String
)
