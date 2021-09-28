package com.mera.weathertestapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

interface BaseViewModel {
    fun getErrorLiveData(): LiveData<Throwable>
}

abstract class BaseViewModelImpl : ViewModel(), BaseViewModel, CoroutineScope by MainScope() {

    private val errorLiveData = MutableLiveData<Throwable>()

    override fun getErrorLiveData(): LiveData<Throwable> = errorLiveData

    protected fun postError(error: Throwable) {
        error.printStackTrace()
        errorLiveData.postValue(error)
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

}