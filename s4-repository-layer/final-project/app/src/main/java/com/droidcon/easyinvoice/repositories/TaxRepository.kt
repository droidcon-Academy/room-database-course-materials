package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.entities.Tax
import kotlinx.coroutines.flow.Flow

interface TaxRepository {
    suspend fun addUpdateTax(tax: Tax)
    suspend fun deleteTax(id: Int?)
    fun getTaxes(): Flow<List<Tax>>
}