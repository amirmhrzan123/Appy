package com.ebpearls.sample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ebpearls.dwell.util.LoadingStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel<N> : ViewModel() {
    private var navigator: N? = null
    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    val viewModelJob = Job()

    /**
     * This is the scope for all coroutines launched by [PlantDetailViewModel].
     *
     * Since we pass [viewModelJob], you can cancel all coroutines launched by [viewModelScope] by calling
     * viewModelJob.cancel().  This is called in [onCleared].
     */
    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    /**
     * livedata to observe  loading from network
     */
    var isLoadingLiveData = MutableLiveData<LoadingStatus>()


    fun getNavigator() = navigator!!

    fun setNavigator(navigator: N) {
        this.navigator = navigator
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}