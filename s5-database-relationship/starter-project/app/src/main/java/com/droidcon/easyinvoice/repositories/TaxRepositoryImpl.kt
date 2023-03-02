package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.daos.TaxDao
import com.droidcon.easyinvoice.data.entities.Tax
import kotlinx.coroutines.flow.Flow

class TaxRepositoryImpl(
    private val dao: TaxDao
) : TaxRepository {
    override suspend fun addUpdateTax(tax: Tax) {
        dao.addUpdateTax(tax)
    }

    override fun getTaxes(): Flow<List<Tax>> {
        return dao.getTaxes()
    }

    override suspend fun deleteTax(id: Int?) {
        val tax = Tax("", 0.0).also { it.id = id }
        dao.deleteTax(tax)
    }
}