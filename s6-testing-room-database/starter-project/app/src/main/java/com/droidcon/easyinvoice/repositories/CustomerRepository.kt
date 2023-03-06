package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.entities.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun addUpdateCustomer(customer: Customer)
    fun getCustomers(): Flow<List<Customer>>
    suspend fun deleteCustomer(id: Int?)
}