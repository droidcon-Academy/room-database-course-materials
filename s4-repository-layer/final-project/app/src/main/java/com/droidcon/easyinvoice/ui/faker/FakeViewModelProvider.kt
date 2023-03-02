package com.droidcon.easyinvoice.ui.faker

import com.droidcon.easyinvoice.data.entities.Business
import com.droidcon.easyinvoice.data.entities.Customer
import com.droidcon.easyinvoice.data.entities.Tax
import com.droidcon.easyinvoice.repositories.BusinessRepository
import com.droidcon.easyinvoice.repositories.CustomerRepository
import com.droidcon.easyinvoice.repositories.TaxRepository
import com.droidcon.easyinvoice.ui.home.customers.CustomersViewModel
import com.droidcon.easyinvoice.ui.home.mybusinesses.MyBusinessViewModel
import com.droidcon.easyinvoice.ui.home.taxes.TaxesViewModel
import kotlinx.coroutines.flow.Flow

/*
* Currently there is a problem with *Jetpack Compose Preview* & *Hilt*
* Jetpack compose is not able to inject using hiltViewModel() to generate Compose Previews
* In future when both these libraries will be compatible, we can remove this object
* But for now, to see preview, we can use this FakeViewModelProvider
* */
object FakeViewModelProvider {

    fun provideMyBusinessViewModel(): MyBusinessViewModel = MyBusinessViewModel(businessRepo)

    fun provideCustomerViewModel(): CustomersViewModel = CustomersViewModel(customerRepo)

    fun provideTaxesViewModel(): TaxesViewModel = TaxesViewModel(taxRepo)

    private val businessRepo = object : BusinessRepository {
        override suspend fun addUpdateBusiness(business: Business) {
            TODO("Not yet implemented")
        }

        override fun getBusinesses(): Flow<List<Business>> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteMyBusiness(id: Int?) {
            TODO("Not yet implemented")
        }
    }

    private val customerRepo = object : CustomerRepository {
        override suspend fun addUpdateCustomer(customer: Customer) {
            TODO("Not yet implemented")
        }

        override fun getCustomers(): Flow<List<Customer>> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCustomer(id: Int?) {
            TODO("Not yet implemented")
        }
    }

    private val taxRepo = object : TaxRepository {
        override suspend fun addUpdateTax(tax: Tax) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteTax(id: Int?) {
            TODO("Not yet implemented")
        }

        override fun getTaxes(): Flow<List<Tax>> {
            TODO("Not yet implemented")
        }

    }
}