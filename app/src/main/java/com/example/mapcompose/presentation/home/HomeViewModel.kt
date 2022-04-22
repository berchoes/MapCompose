package com.example.mapcompose.presentation.home

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.mapcompose.base.BaseViewModel
import com.example.mapcompose.common.Resource
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.domain.usecase.GetStationsUseCase
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Berk Ç. on 20.04.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
) : BaseViewModel() {

    var stations by mutableStateOf<List<Station>>(emptyList())
        private set

    var selectedStation by mutableStateOf<Station?>(null)
        private set

    init {
        getStations()
    }

    private fun getStations() {
        isLoading = true
        getStationsUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> {
                    errorMessage = it.message
                }
                is Resource.Success -> {
                    stations = it.data
                }
            }
            isLoading = false
        }.launchIn(viewModelScope)
    }

    fun setTappedStation(station: Station?){
        selectedStation = station
        stations.forEach { it.isSelected = it == station }
    }

    fun bitmapDescriptor(
        context: Context,
        vectorResId: Int
    ): BitmapDescriptor? {

        // retrieve the actual drawable
        val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bm = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // draw it onto the bitmap
        val canvas = android.graphics.Canvas(bm)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }
}