package com.example.mapcompose.domain.usecase

import com.example.mapcompose.common.Resource
import com.example.mapcompose.data.dto.toStationModel
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.domain.repository.StationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Berk Ç. on 20.04.2022.
 */
class GetStationsUseCase @Inject constructor(private val repository: StationsRepository) {

    operator fun invoke(): Flow<Resource<List<Station>>> = flow {
        try {
            val stations = repository.getStations().map { it.toStationModel() }
            emit(Resource.Success<List<Station>>(stations))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred :("))
        }
    }
}