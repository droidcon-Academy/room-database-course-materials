package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.daos.InvoiceDao
import com.droidcon.easyinvoice.data.entities.Invoice
import com.droidcon.easyinvoice.data.entities.InvoiceItem
import com.droidcon.easyinvoice.data.entities.InvoiceWithItems
import kotlinx.coroutines.flow.Flow

class InvoiceRepositoryImpl(
    private val dao: InvoiceDao
) : InvoiceRepository {

    override suspend fun addInvoice(invoice: Invoice): Long {
        return dao.addUpdateInvoice(invoice)
    }

    override suspend fun addInvoiceItem(invoiceItem: InvoiceItem) {
        dao.addUpdateInvoiceItem(invoiceItem)
    }

    override suspend fun updateInvoice(invoice: Invoice) {
        dao.addUpdateInvoice(invoice)
    }

    override suspend fun updateInvoiceItem(invoiceItem: InvoiceItem) {
        dao.addUpdateInvoiceItem(invoiceItem)
    }

    override suspend fun deleteInvoiceItem(id: Int?) {
        val item = InvoiceItem("", 0.0, 0.0, -1).also { it.id = id }
        dao.deleteInvoiceItem(item)
    }

    override fun getInvoices(): Flow<List<InvoiceWithItems>> {
        return dao.getInvoices()
    }

    override fun getInvoiceItems(invoiceId: Int): Flow<List<InvoiceItem>> {
        return dao.getInvoiceItems(invoiceId)
    }

    override suspend fun setPaidStatus(invoiceId: Int, status: Boolean) {
        dao.setPaidStatus(invoiceId, status)
    }

    override suspend fun deleteInvoice(id: Int?) {
        val invoice = Invoice(business_id = -1, customer_id = -1, tax_id = -1).also { it.id = id }
        dao.deleteInvoice(invoice)
    }
}