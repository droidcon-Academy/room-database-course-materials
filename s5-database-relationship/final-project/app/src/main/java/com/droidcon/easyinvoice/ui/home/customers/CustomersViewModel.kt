package com.droidcon.easyinvoice.ui.home.customers

import androidx.lifecycle.viewModelScope
import com.droidcon.easyinvoice.data.entities.Customer
import com.droidcon.easyinvoice.repositories.CustomerRepository
import com.droidcon.easyinvoice.ui.home.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val repository: CustomerRepository
) : BaseViewModel<Customer>() {

    val name = MutableStateFlow("")
    val address = MutableStateFlow("")
    val phone = MutableStateFlow("")
    val email = MutableStateFlow("")

    private val _customers = MutableStateFlow<List<Customer>>(listOf())
    val customers: StateFlow<List<Customer>> = _customers

    init {
        getCustomers()
    }


    override fun validateInputs() {
        mAreInputsValid.value =
            name.value.trim().isNotEmpty() && address.value.trim().isNotEmpty() &&
                    phone.value.trim().isNotEmpty() && email.value.trim().isNotEmpty()
        /* @Todo more specific validations */
    }

    override fun clearInputs() {
        name.value = ""
        address.value = ""
        phone.value = ""
        email.value = ""
    }

    override fun setUpdating(item: Customer) {
        super.setUpdating(item)
        name.value = item.name
        address.value = item.address
        phone.value = item.phone
        email.value = item.email
    }

    fun addUpdateCustomer() = viewModelScope.launch {
        val customer = Customer(name.value, address.value, phone.value, email.value).also { it.id = isUpdating.value?.id }
        repository.addUpdateCustomer(customer)
        resetUpdating()
        clearInputs()
    }

    fun deleteCustomer() = viewModelScope.launch {
        repository.deleteCustomer(isUpdating.value?.id)
        resetUpdating()
    }

    private fun getCustomers() = viewModelScope.launch {
        repository.getCustomers().collect {
            _customers.value = it
        }
    }

}