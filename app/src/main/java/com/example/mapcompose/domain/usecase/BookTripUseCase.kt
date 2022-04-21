package com.example.mapcompose.domain.usecase

import com.example.mapcompose.common.Resource
import com.example.mapcompose.data.dto.toBookResponseModel
import com.example.mapcompose.domain.model.BookResponse
import com.example.mapcompose.domain.repository.StationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Berk Ç. on 20.04.2022.
 */
class BookTripUseCase @Inject constructor(private val repository: StationsRepository) {

    operator fun invoke(stationId: Int, tripId: Int): Flow<Resource<BookResponse>> = flow {
        try {
            val response = repository.bookTrip(stationId, tripId).toBookResponseModel()
            emit(Resource.Success<BookResponse>(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred :("))
        }
    }
}