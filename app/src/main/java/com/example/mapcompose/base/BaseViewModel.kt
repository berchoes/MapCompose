package com.example.mapcompose.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

/**
 * Created by Berk Ç. on 21.04.2022.
 */


abstract class BaseViewModel: ViewModel() {

    var isLoading by mutableStateOf(false)
        protected set

    var errorMessage by mutableStateOf<String?>(null)
        protected set

    fun convertToJson(item : Any): String {
        return Gson().toJson(item)
    }
}