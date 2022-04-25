package com.example.mapcompose.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by Berk Ç. on 21.04.2022.
 */


abstract class BaseViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        protected set

    var errorMessage by mutableStateOf("")
        protected set

    var isDialogVisible by mutableStateOf(false)
        private set

    fun showDialog(isShowing: Boolean) {
        isDialogVisible = isShowing
        errorMessage = ""
    }

    fun convertToJson(item: Any): String {
        return Gson().toJson(item)
    }

    protected fun <T> Flow<T>.fetch(action: (T) -> Unit) {
        isLoading = true
        this.onEach {
            action.invoke(it)
            isLoading = false
        }
            .catch { e -> println(e.cause?.message) }
            .launchIn(viewModelScope)

    }
}
