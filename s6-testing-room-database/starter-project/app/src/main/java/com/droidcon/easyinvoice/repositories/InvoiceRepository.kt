package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.entities.Invoice
import com.droidcon.easyinvoice.data.entities.InvoiceItem
import com.droidcon.easyinvoice.data.entities.InvoiceWithItems
import kotlinx.coroutines.flow.Flow

interface InvoiceRepository {
    suspend fun addInvoice(invoice: Invoice): Long
    suspend fun addInvoiceItem(invoiceItem: InvoiceItem)

    suspend fun updateInvoice(invoice: Invoice)
    suspend fun updateInvoiceItem(invoiceItem: InvoiceItem)

    suspend fun deleteInvoice(id: Int?)
    suspend fun deleteInvoiceItem(id: Int?)

    fun getInvoices(): Flow<List<InvoiceWithItems>>
    fun getInvoiceItems(invoiceId: Int): Flow<List<InvoiceItem>>

    suspend fun setPaidStatus(invoiceId: Int, status: Boolean)
}