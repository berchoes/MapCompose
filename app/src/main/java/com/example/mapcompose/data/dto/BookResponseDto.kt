package com.example.mapcompose.data.dto


import com.example.mapcompose.domain.model.BookResponse
import com.google.gson.annotations.SerializedName

data class BookResponseDto(
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("time")
    val time: String?
)


fun BookResponseDto.toBookResponseModel() = BookResponse(
    busName = this.busName ?: "noName",
    id = this.id,
    time = this.time ?: "noTimeSpecified"
)