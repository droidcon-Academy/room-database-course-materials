package com.droidcon.easyinvoice.ui.home.mybusinesses

import androidx.lifecycle.viewModelScope
import com.droidcon.easyinvoice.data.entities.Business
import com.droidcon.easyinvoice.repositories.BusinessRepository
import com.droidcon.easyinvoice.ui.home.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBusinessViewModel @Inject constructor(
    private val repository: BusinessRepository
) : BaseViewModel<Business>() {

    val name = MutableStateFlow("")
    val address = MutableStateFlow("")
    val phone = MutableStateFlow("")
    val email = MutableStateFlow("")

    private val _businesses = MutableStateFlow<List<Business>>(listOf())
    val businesses: StateFlow<List<Business>> = _businesses

    init {
        getBusinesses()
    }


    override fun validateInputs() {
        mAreInputsValid.value = name.value.trim().isNotEmpty() && address.value.trim().isNotEmpty() &&
                phone.value.trim().isNotEmpty() && email.value.trim().isNotEmpty()
        /* @Todo more specific validation */
    }

    override fun clearInputs() {
        name.value = ""
        address.value = ""
        phone.value = ""
        email.value = ""
    }

    override fun setUpdating(item: Business) {
        name.value = item.name
        address.value = item.address
        phone.value = item.phone
        email.value = item.email
        super.setUpdating(item)
    }

    fun addUpdateBusiness() = viewModelScope.launch {
        val business = Business(name.value, address.value, phone.value, email.value).also {
            it.id = isUpdating.value?.id
        }
        repository.addUpdateBusiness(business)
        resetUpdating()
        clearInputs()
    }

    fun getBusinesses() = viewModelScope.launch {
        repository.getBusinesses().collect {
            _businesses.value = it
        }
    }

    fun deleteBusiness() = viewModelScope.launch {
        repository.deleteMyBusiness(isUpdating.value?.id)
    }

}