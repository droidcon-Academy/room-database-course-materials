package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.daos.CustomerDao
import com.droidcon.easyinvoice.data.entities.Customer
import kotlinx.coroutines.flow.Flow

class CustomerRepositoryImpl(
    private val dao: CustomerDao
) : CustomerRepository {

    override suspend fun addUpdateCustomer(customer: Customer) {
        dao.addUpdateCustomer(customer)
    }

    override fun getCustomers(): Flow<List<Customer>> {
        return dao.getCustomers()
    }

    override suspend fun deleteCustomer(id: Int?) {
        val customer = Customer("", "", "", "").also {
            it.id = id
        }
        dao.deleteCustomer(customer)
    }
}