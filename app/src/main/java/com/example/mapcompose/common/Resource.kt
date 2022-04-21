package com.example.mapcompose.common

/**
 * Created by Berk Ç. on 20.04.2022.
 */

sealed class Resource<out T>{
    data class Success<T>(val data: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
}
