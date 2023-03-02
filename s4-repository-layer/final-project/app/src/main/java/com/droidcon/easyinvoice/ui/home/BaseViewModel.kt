package com.droidcon.easyinvoice.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<E : Any> : ViewModel() {

    private val mIsUpdating = MutableStateFlow<E?>(null)
    val isUpdating: StateFlow<E?> = mIsUpdating

    protected val mAreInputsValid = MutableStateFlow(false)
    val areInputsValid: StateFlow<Boolean> = mAreInputsValid

    abstract fun validateInputs()
    abstract fun clearInputs()

    open fun setUpdating(item: E) {
        mIsUpdating.value = item
        validateInputs()
    }

    open fun resetUpdating() {
        mIsUpdating.value = null
    }

}