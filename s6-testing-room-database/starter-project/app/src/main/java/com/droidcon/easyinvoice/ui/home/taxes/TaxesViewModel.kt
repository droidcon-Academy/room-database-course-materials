package com.droidcon.easyinvoice.ui.home.taxes

import androidx.lifecycle.viewModelScope
import com.droidcon.easyinvoice.data.entities.Tax
import com.droidcon.easyinvoice.repositories.TaxRepository
import com.droidcon.easyinvoice.ui.home.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaxesViewModel @Inject constructor(
    private val repository: TaxRepository
) : BaseViewModel<Tax>() {

    val desc = MutableStateFlow("")
    val value = MutableStateFlow("")

    private val _taxes = MutableStateFlow<List<Tax>>(listOf())
    val taxes: StateFlow<List<Tax>> = _taxes

    init {
        getTaxes()
    }

    override fun validateInputs() {
        val validate = desc.value.trim().isNotEmpty() && value.value.trim().isNotEmpty()
        mAreInputsValid.value = validate
        /* @Todo more specific validations */
    }

    override fun clearInputs() {
        desc.value = ""
        value.value = ""
    }

    fun addUpdateTax() = viewModelScope.launch {
        val tax = Tax(desc.value, value.value.toDouble()).also { it.id = isUpdating.value?.id }
        repository.addUpdateTax(tax)
        resetUpdating()
        clearInputs()
    }

    fun deleteTax() = viewModelScope.launch {
        repository.deleteTax(isUpdating.value?.id)
        resetUpdating()
    }

    private fun getTaxes() = viewModelScope.launch {
        repository.getTaxes().collect {
            _taxes.value = it
        }
    }
}