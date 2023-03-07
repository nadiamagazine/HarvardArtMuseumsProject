package com.example.harvardartmuseumsproject

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Success<out T : Any>(val data: T) : ScreenState<T>()
    data class Error(val message: String) : ScreenState<Nothing>()
}
