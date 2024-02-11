package com.arif.arifiot.common

sealed class Resource<out T> {

    data class Success<out R>(val data: R) : Resource<R>()
    data class Error(val msg: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}