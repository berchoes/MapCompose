package com.example.mapcompose.util

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Berk Ç. on 21.04.2022.
 */


fun String.convertToLatLng(): LatLng {
    val delimiter = ","
    val list = this.split(delimiter)
    return LatLng(list[0].toDouble(),list[1].toDouble())
}