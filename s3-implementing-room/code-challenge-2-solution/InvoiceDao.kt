package com.droidcon.easyinvoice.data.daos

import androidx.room.*
import com.droidcon.easyinvoice.data.entities.Invoice
import com.droidcon.easyinvoice.data.entities.InvoiceItem
import com.droidcon.easyinvoice.data.entities.InvoiceWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateInvoice(invoice: Invoice): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateInvoiceItem(invoiceItem: InvoiceItem)

    @Transaction
    @Query("SELECT * FROM invoices")
    fun getInvoices(): Flow<List<InvoiceWithItems>>

    @Query("SELECT * FROM invoice_items WHERE invoice_id=:invoiceId")
    fun getInvoiceItems(invoiceId: Int): Flow<List<InvoiceItem>>

    @Delete
    suspend fun deleteInvoice(invoice: Invoice)

    @Delete
    suspend fun deleteInvoiceItem(invoiceItem: InvoiceItem)

    @Query("UPDATE invoices SET is_paid = :status WHERE id=:invoiceId")
    suspend fun setPaidStatus(invoiceId: Int, status: Boolean)
}